package dobong.life.crolling;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void writeEntitiesToCsv(String filePath, TestEntity entity) {
        boolean isFileEmpty = !new File(filePath).exists(); // 파일이 없는 경우 헤더 작성

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            // 파일이 없으면 헤더 작성
            if (isFileEmpty) {
                String[] header = {"Title", "Poster URL", "Menu Items", "Tag Items", "Main Category", "Sub Category", "Address"};
                writer.writeNext(header);
            }

            // 데이터 작성
            String menu = String.join(", ", entity.getMenuItems()); // 메뉴 아이템을 쉼표로 구분
            String tags = String.join(", ", entity.getTagItems()); // 태그 아이템을 쉼표로 구분
            String[] data = {
                    entity.getTitle(),
                    entity.getPosterUrl(),
                    menu,
                    tags,
                    entity.getMainCategory(),
                    entity.getSubCategory(),
                    entity.getAddress()
            };
            writer.writeNext(data);

            System.out.println("CSV 파일 작성 완료: " + filePath);
        } catch (IOException e) {
            System.out.println("CSV 파일 작성 중 오류 발생: " + e.getMessage());
        }
    }
}
