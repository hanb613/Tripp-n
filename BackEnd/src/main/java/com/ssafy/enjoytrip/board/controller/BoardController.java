package com.ssafy.enjoytrip.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionLikeDto;
import com.ssafy.enjoytrip.board.model.BoardCommentDto;
import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardLikeDto;
import com.ssafy.enjoytrip.board.model.BoardParameterDto;
import com.ssafy.enjoytrip.board.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

// http://localhost/swagger-ui.html
@Api("게시판 컨트롤러  API V1")
@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("writeArticle - 호출");
		if (boardService.writeArticle(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
		logger.info("listArticle - 호출("+ boardParameterDto.getBoardType() +")");
		Map<String, Object> resultMap = boardService.listArticle(boardParameterDto);

		System.out.println(resultMap.get("articleList"));
		//articleList: boardDto 리스트, likedList: 각 게시물 좋아요수 카운트한 정수 리스트
		
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/{boardNo}")
	public ResponseEntity<Map<String, Object>> getArticle(@PathVariable("boardNo") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardNo) throws Exception {
		logger.info("getArticle - 호출 : " + boardNo);
		Map<String, Object> resultMap = new HashMap<>();
		//boardService.updateHit(boardNo);
		//return new ResponseEntity<BoardDto>(boardService.getArticle(boardNo), HttpStatus.OK);
        resultMap.put("article", boardService.getArticle(boardNo));
        resultMap.put("likedUsers", boardService.getLikeList(boardNo));
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("modifyArticle - 호출 {}", boardDto);
		
		if (boardService.modifyArticle(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<String> deleteArticle(@PathVariable("boardNo") @ApiParam(value = "살제할 글의 글번호.", required = true) int boardNo) throws Exception {
		logger.info("deleteArticle - 호출");
		if (boardService.deleteArticle(boardNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/* 게시물 댓글 */
	@ApiOperation(value = "게시판 댓글 작성", notes = "새로운 게시글 댓글을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/comment")
	public ResponseEntity<String> writeComment(@RequestBody @ApiParam(value = "게시글 댓글 정보.", required = true) BoardCommentDto boardCommentDto) throws Exception {
		logger.info("writeComment - 호출");
		if (boardService.writeComment(boardCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/comment/{boardNo}")
	public ResponseEntity<List<BoardCommentDto>> listComment(@PathVariable("boardNo") int boardNo) throws Exception {
		logger.info("listComment - 호출");
		System.out.println(boardService.listComment(boardNo));
		return new ResponseEntity<List<BoardCommentDto>>(boardService.listComment(boardNo), HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/{commentNo}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentNo") @ApiParam(value = "삭제할 댓글의  댓글 번호.", required = true) int commentNo) throws Exception {
		logger.info("deleteComment - 호출");
		if (boardService.deleteComment(commentNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/* 게시물 좋아요 */
    @PostMapping("/like")
    public ResponseEntity<String> likeBoard(@RequestBody BoardLikeDto boardLikeDto) throws Exception {
		logger.info("likeBoard - 호출: "+boardLikeDto.getUserNo()+" liked "+ boardLikeDto.getBoardNo());
		if (boardService.likeBoard(boardLikeDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}