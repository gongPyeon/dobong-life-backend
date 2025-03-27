package dobong.life.base.store.controller;

import dobong.life.base.store.controller.response.*;
import dobong.life.base.store.service.StoreService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dobong")
@Slf4j
@SecurityRequirement(name = "BearerAuth")
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "홈 상점 목록 조회", description = "홈 화면 상점 리스트를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요", //
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요", //
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "도메인을 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("/home")
    public BaseResponse<StoresResDTO> viewStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        StoresResDTO resDTO = storeService.getStoreList(userId);
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "카테고리 상점 목록 조회", description = "카테고리 별 상점 리스트를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "해시태그를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "도메인을 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("/{categoryName}/{page}")
    public BaseResponse<StoresByIdResDTO> viewStoreListByCategory(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                  @PathVariable String categoryName, @PathVariable int page){
        Long userId = userPrincipal.getId();
        StoresByIdResDTO resDTO = storeService.getStoreListByCategory(categoryName, userId, page);
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "상점 검색", description = "상점을 검색한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "도메인을 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("/search")
    public BaseResponse<StoresByQueryResDTO> searchStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                             @RequestParam(required = false, defaultValue = "") String query,
                                                             @RequestParam(required = false) List<String> filter){
        Long userId = userPrincipal.getId();
        StoresByQueryResDTO resDTO = storeService.searchStoreList(userId, query, filter);
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "찜목록 조회", description = "찜목록(카테고리 구분 없이)을 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "도메인을 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("/like")
    public BaseResponse<StoreLikeResDTO> viewStoreLike(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        StoreLikeResDTO resDTO = storeService.getStoreLikeList(userId);
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "상점 좋아요", description = "상점을 좋아요 처리한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "해당 사용자를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "도메인을 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "DB 작업을 실패했어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @PostMapping("/item/{storeId}/like")
    public BaseResponse<String> updateStoreLike(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                 @PathVariable Long storeId){
        Long userId = userPrincipal.getId();
        String message = storeService.updateStoreLikeByUser(userId, storeId);
        return new BaseResponse<>(message);
    }
}
