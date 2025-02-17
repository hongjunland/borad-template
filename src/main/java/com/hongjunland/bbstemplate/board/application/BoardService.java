package com.hongjunland.bbstemplate.board.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongjunland.bbstemplate.board.domain.Board;
import com.hongjunland.bbstemplate.board.dto.BoardRequest;
import com.hongjunland.bbstemplate.board.dto.BoardResponse;
import com.hongjunland.bbstemplate.board.infrastructure.BoardJpaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;

//    @Transactional
//    public BoardResponse createBoard(BoardRequest request) {
//        Board board = boardJpaRepository.save(
//                Board.builder()
//                        .name(request.name())
//                        .description(request.description())
//                        .build()
//        );
//        return new BoardResponse(board.getId(), board.getName(), board.getDescription());
//    }
    @Transactional
    public BoardResponse createBoard(BoardRequest request) {
        Board board = Board.create(request.name(), request.description());
        boardJpaRepository.save(board);
        return BoardResponse.builder()
                .boardId(board.getId())
                .name(board.getName())
                .description(board.getDescription())
                .build();
    }

    public BoardResponse getBoardById(Long boardId) {
        Board Board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시판이 존재하지 않습니다."));
        BoardResponse boardResponse = BoardResponse.builder()
                .boardId(Board.getId())
                .name(Board.getName())
                .description(Board.getDescription())
                .build();
        return boardResponse;
    }

    public List<BoardResponse> getAllBoards() {
        List<Board> BoardList = boardJpaRepository.findAll();
        List<BoardResponse> responseList = BoardList.stream()
                .map((entity) ->
                        BoardResponse.builder()
                                .boardId(entity.getId())
                                .name(entity.getName())
                                .description(entity.getDescription())
                                .build()
                )
                .toList();
        return responseList;
    }
    @Transactional
    public void deleteBoard(Long boardId) {
        if (!boardJpaRepository.existsById(boardId)) {
            throw new EntityNotFoundException("해당 게시판이 존재하지 않습니다.");
        }
        boardJpaRepository.deleteById(boardId);
    }

    @Transactional
    public BoardResponse updateBoard(Long boardId, BoardRequest request) {
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시판이 존재하지 않습니다."));
        board.update(request.name(), request.description());
        return new BoardResponse(board.getId(), board.getName(), board.getDescription());
    }
}
