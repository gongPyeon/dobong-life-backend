package dobong.life.global.auth.controller.request;

import dobong.life.global.auth.support.AuthFixture;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserSignUpDtoTest {

    @Nested
    @DisplayName("사용자 ID 검증 시")
    class UserIdTes{

        @ParameterizedTest
        @CsvSource({
                "member, member",
                "hello0331, hello0331"
        })
        @DisplayName("성공")
        void success(String validUsrId, String expectedId){

            // given & when
            UserSignUpDto result = AuthFixture.userSignUpDtoById(validUsrId);

            // then
            assertThat(result.getId()).isEqualTo(expectedId);

        }

//        // TODO: Valid가 없으면 DTO는 검증하지 않는다 Validator를 만들까?
//        @ParameterizedTest
//        @CsvSource({
//                "", "000000000000000000000"
//        })
//        @DisplayName("유효하지 않은 아이디 (최소 1개 이상, 최대 20자가 아닐 경우)")
//        void throwExceptionWhenInvalidLength(String invalidUsrId){
//
//            // given & when & then
//            assertThatThrownBy(() -> AuthFixture.userSignUpDtoById(invalidUsrId))
//                    .isInstanceOf(MethodArgumentNotValidException.class);
//        }
//
//        @ParameterizedTest
//        @CsvSource({
//                "안녕", "!!!", "안녕abc"
//        })
//        @DisplayName("유효하지 않은 아이디 (알파벳 또는 숫자가 아닐경우)")
//        void throwExceptionWhenInvalidFormat(String invalidUsrId){
//
//            // given & when & then
//            assertThatThrownBy(() -> AuthFixture.userSignUpDtoById(invalidUsrId))
//                    .isInstanceOf(MethodArgumentNotValidException.class);
//        }

    }

    @Nested
    @DisplayName("사용자 Pwd 검증 시")
    class UserPwdTes{

        @ParameterizedTest
        @CsvSource({
                "Oauth2!, Oauth2!",
                "hello30~, hello30~"
        })
        @DisplayName("성공")
        void success(String validUsrPwd, String expectedPwd){

            // given & when
            UserSignUpDto result = AuthFixture.userSignUpDtoByPwd(validUsrPwd);

            // then
            assertThat(result.getPwd()).isEqualTo(expectedPwd);

        }

//        @ParameterizedTest
//        @CsvSource({
//                "abc!00", "abc!0000000000000000000000000000000000000"
//        })
//        @DisplayName("유효하지 않은 아이디 (최소 8개 이상, 최대 30자가 아닐 경우)")
//        void throwExceptionWhenInvalidLength(String invalidUsrPwd){
//
//            // given & when & then
//            assertThatThrownBy(() -> AuthFixture.userSignUpDtoByPwd(invalidUsrPwd))
//                    .isInstanceOf(MethodArgumentNotValidException.class);
//        }
//
//        @ParameterizedTest
//        @CsvSource({
//                "Oauth2****안녕", "!!!!!!!!!", "abcabcabc",
//                "123123123", "!a!a!a!a", "!1!1!1!1", "a1a1a1a1"
//        })
//        @DisplayName("유효하지 않은 아이디 (알파벳, 숫자, 특수문자을 모두 포함하지 않거나 다른문자를 포함시킬경우)")
//        void throwExceptionWhenInvalidFormat(String invalidUsrPwd){
//
//            // given & when & then
//            assertThatThrownBy(() -> AuthFixture.userSignUpDtoByPwd(invalidUsrPwd))
//                    .isInstanceOf(MethodArgumentNotValidException.class);
//        }

    }

    @Nested
    @DisplayName("사용자 NickName 검증 시")
    class UserNickNameTes{

        @ParameterizedTest
        @CsvSource({
                "user0225, user0225",
                "김길동, 김길동"
        })
        @DisplayName("성공")
        void success(String validUsrName, String expectedName){

            // given & when
            UserSignUpDto result = AuthFixture.userSignUpDtoByName(validUsrName);

            // then
            assertThat(result.getNickname()).isEqualTo(expectedName);

        }

//        @ParameterizedTest
//        @CsvSource({
//                "", "abc0000000000000000000000000000000000000"
//        })
//        @DisplayName("유효하지 않은 아이디 (최소 1개 이상, 최대 30자가 아닐 경우)")
//        void throwExceptionWhenInvalidLength(String invalidUsrName){
//
//            // given & when & then
//            assertThatThrownBy(() -> AuthFixture.userSignUpDtoByName(invalidUsrName))
//                    .isInstanceOf(MethodArgumentNotValidException.class);
//        }
//
//        @ParameterizedTest
//        @CsvSource({
//                "abc123안녕!", "?!?!?!"
//        })
//        @DisplayName("유효하지 않은 아이디 (알파벳, 숫자, 한글 외 다른 문자를 포함시킬경우)")
//        void throwExceptionWhenInvalidFormat(String invalidUsrName){
//
//            // given & when & then
//            assertThatThrownBy(() -> AuthFixture.userSignUpDtoByName(invalidUsrName))
//                    .isInstanceOf(MethodArgumentNotValidException.class);
//        }

    }

}