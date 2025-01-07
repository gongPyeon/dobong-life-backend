package dobong.life.crolling;

import org.openqa.selenium.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.*;
import java.util.stream.Collectors;

//defaults write com.google.keystone.agent checkinterval 0 업데이트 주기 막기

public class TestDriverUtil {

    private static final int MAX_RESTAURANTS = 80;
    private static String WEB_DRIVER_PATH = "/opt/homebrew/bin/chromedriver";

    public static WebDriver getChromeDriver() {

        if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        }

        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");

        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }

    @Value("#{resource['driver.chrome.driver_path']}")
    public void initDriver(String path) {
        WEB_DRIVER_PATH = path;
    }

    public static void quit(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.quit();
        }
    }

    public static void close(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.close();
        }
    }

    public void waitAndPrint() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<?>> futures = new ArrayList<>();
        WebDriver driver = TestDriverUtil.getChromeDriver();
        Set<String> processedTitles = new HashSet<>();

        try {
            String url = "https://www.diningcode.com/list.dc?query=%EB%8F%84%EB%B4%89%EA%B5%AC";

            if (!ObjectUtils.isEmpty(driver)) {
                driver.get(url);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                int lastHeight = ((Long) js.executeScript("return document.body.scrollHeight")).intValue();
                int noNewContentCount = 0;

                while (noNewContentCount < 5 && processedTitles.size() < MAX_RESTAURANTS) {
                    try {
                        // 스크롤 다운
                        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                        Thread.sleep(2000);

                        // SolidBar 처리를 위한 함수
                        processSolidBars(driver, js);

                        // 음식점 정보 찾기 (명시적 대기 추가)
                        List<WebElement> webElementList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.RHeader")));;
                        // 음식점 사진 찾기 div.InfoHeader h2

                        int newItems = processRestaurants(webElementList, processedTitles, executorService, futures);

                        // 새로운 콘텐츠 확인
                        if (newItems == 0) {
                            noNewContentCount++;
                        } else {
                            noNewContentCount = 0;
                        }

                        // 페이지 높이 체크 및 스크롤 조정
                        int newHeight = ((Long) js.executeScript("return document.body.scrollHeight")).intValue();
                        if (newHeight == lastHeight) {
                            performSmallScrolls(js);
                        }
                        lastHeight = newHeight;

                    } catch (InterruptedException e) {
                        System.out.println("스크롤 중 인터럽트 발생: " + e.getMessage());
                        break;
                    }
                }
            }

            // 모든 작업 완료 대기
            waitForAllTasks(futures);

        } finally {
            cleanupResources(executorService, driver);
        }
    }

    private void processSolidBars(WebDriver driver, JavascriptExecutor js) {
        try {
            // 새로운 WebDriverWait 인스턴스 생성
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

            try {
                // SolidBar 요소들을 찾되, 명시적 대기 사용
                List<WebElement> solidBars = shortWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("SolidBar")));

                for (int i = 0; i < solidBars.size(); i++) {
                    try {
                        // 매번 새로 요소를 찾아서 처리
                        List<WebElement> currentSolidBars = driver.findElements(By.className("SolidBar"));
                        if (i < currentSolidBars.size()) {
                            WebElement solidBar = currentSolidBars.get(i);

                            // 요소가 보이는지 JavaScript로 확인
                            Boolean isVisible = (Boolean) js.executeScript(
                                    "var elem = arguments[0]; " +
                                            "return elem.offsetWidth > 0 && elem.offsetHeight > 0;",
                                    solidBar
                            );

                            if (Boolean.TRUE.equals(isVisible)) {
                                // SolidBar 위치로 스크롤
                                js.executeScript("arguments[0].scrollIntoView(true);", solidBar);
                                Thread.sleep(1000);
                                // SolidBar 너머로 스크롤
                                js.executeScript("window.scrollBy(0, 300);");
                                Thread.sleep(1000);
                            }
                        }
                    } catch (StaleElementReferenceException e) {
                        // 개별 요소 처리 중 발생한 stale element 무시하고 계속 진행
                        continue;
                    }
                }
            } catch (TimeoutException e) {
                // SolidBar를 찾지 못한 경우 무시하고 계속 진행
            }
        } catch (Exception e) {
            System.out.println("SolidBar 처리 중 예외 발생: " + e.getMessage());
        }
    }

    private int processRestaurants(List<WebElement> webElementList, Set<String> processedTitles,
                                   ExecutorService executorService, List<Future<?>> futures) {
        int newItems = 0;
        for (WebElement titleElement : webElementList) {
            try {
                String title = titleElement.findElement(By.cssSelector("div.InfoHeader h2")).getText().trim();
                if (!title.isEmpty() && processedTitles.add(title)) { // 100개까지 가서 이렇게 설정
                    newItems++;
                    Future<?> future = executorService.submit(() -> {
                        System.out.println(title);
                    });
                    futures.add(future);

                    String imgUrl = titleElement.findElement(By.cssSelector("img.title")).getAttribute("src");

                    List<WebElement> menu = titleElement.findElements(By.cssSelector("p.Category"));
                    List<String> menuItems = menu.stream()
                            .map(span -> span.getText().replaceAll("\\s+", " ").trim())  // 엔터와 공백 처리
                            .filter(text -> !",".contains(text))  // 쉼표가 포함된 항목 제외
                            .collect(Collectors.toList());

                    List<WebElement> tag = titleElement.findElements(By.cssSelector("div.Hash span"));
                    List<String> tagItems = tag.stream()
                            .map(span -> span.getText().trim()) // 텍스트 추출 및 공백 제거
                            .filter(text -> !",".contains(text)) // 쉼표 제외
                            .collect(Collectors.toList()); // 결과를 리스트로 변환

                    String address = extractAddress(title);

                    TestEntity testEntity = new TestEntity(title, imgUrl, menuItems, tagItems, address);
                    CsvWriter.writeEntitiesToCsv("output.csv", testEntity);
                }
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
        return newItems;
    }

    // 뒤에서부터 공백 전까지 자르는 함수
    private String extractAddress(String address) {
        // 공백을 기준으로 뒤에서부터 자르기
        int lastSpaceIndex = address.lastIndexOf(" "); // 마지막 공백 위치 찾기

        if (lastSpaceIndex != -1) {
            // 마지막 공백 이후의 문자열 반환
            return address.substring(lastSpaceIndex + 1); // 공백 이후의 부분만 반환
        } else {
            // 공백이 없다면 원본 주소 그대로 반환
            return address;
        }
    }

    private void performSmallScrolls(JavascriptExecutor js) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.scrollBy(0, -100);");
            Thread.sleep(500);
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(500);
        }
    }

    private void waitForAllTasks(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                System.out.println("작업 처리 중 오류 발생: " + e.getMessage());
            }
        }
    }

    private void cleanupResources(ExecutorService executorService, WebDriver driver) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        quit(driver);
    }
}
