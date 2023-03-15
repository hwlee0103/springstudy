package com.example.springstudy.springstart.service.board;

import com.example.springstudy.springstart.entity.board.BoardEntity;
import com.example.springstudy.springstart.repository.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    // 의존성 주입 DI
    // 생성자 주입방식
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //#region - 조회

    /**
     * 게시글 목록 조회
     * @return
     */
    public List<BoardEntity> findBoardList() {
        List<BoardEntity> boardList = boardRepository.findAll();

        if(CollectionUtils.isEmpty(boardList) == false) {
            // ?

        } else {
            // TODO: exception
        }

        return boardList;
    }

    /**
     * 게시글 조회 by Seq
     * @param boardSeq
     * @return
     */
    public BoardEntity findBoardById(Integer boardSeq) {
        //BoardEntity boardItem = boardRepository.getReferenceById(boardSeq);
        Optional<BoardEntity> boardItem = boardRepository.findById(boardSeq);
        BoardEntity resultItem = new BoardEntity();
        //validation
        if(boardItem.isPresent() == true) {
            resultItem = boardItem.get();
        } else {
            // TODO : exception
            return boardItem.orElse(null);
        }

        return resultItem;
    }

    //#endregion

    //#region - 저장

    /**
     * 게시글 저장
     * @param boardEntity
     */
    public boolean saveBoard(BoardEntity boardEntity) {
        boolean isValid = this.validBoard(boardEntity);
        boolean result = false;

        if(isValid == true) {
            boardEntity.setCreatedBy(boardEntity.getBoardWriter());
            boardEntity.setModifiedBy(boardEntity.getBoardWriter());
            BoardEntity saved = boardRepository.saveAndFlush(boardEntity);

            // save 하고 난 return data 도 validation 필요한지?
            result = true;
        } else {
            // TODO: not valid value - exception
        }

        return result;
    }

    /**
     * 게시글 수정
     * @param boardEntity
     * @return
     */
    public boolean updateBoard(BoardEntity boardEntity) {
        boolean isValid = this.validBoard(boardEntity);
        boolean result = false;

        if(isValid == true) {
            boardEntity.setModifiedBy(boardEntity.getBoardWriter());
            BoardEntity saved = boardRepository.saveAndFlush(boardEntity);
            result = true;
        } else {
            // TODO: not valid value - exception
        }

        return result;
    }

    //#endregion

    //#region - 삭제

    /**
     * 게시글 삭제
     * @param boardSeq
     */
    public void deleteBoard(Integer boardSeq) {
        if(ObjectUtils.isEmpty(boardSeq) == false){
            boardRepository.deleteById(boardSeq);
        } else {
            // TODO: exception
        }
    }

    //#endregion

    //#region - validation

    /**
     * input check
     * @param boardEntity
     * @return
     */
    private boolean validBoard(BoardEntity boardEntity) {
        boolean result = false;

        result = ObjectUtils.isEmpty(boardEntity) != true;

        return result;
    }

    //#endregion

}
