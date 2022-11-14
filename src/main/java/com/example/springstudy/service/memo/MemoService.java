package com.example.springstudy.service.memo;

import com.example.springstudy.entity.memo.MemoEntity;
import com.example.springstudy.repository.memo.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {

        this.memoRepository = memoRepository;
    }

    //#region - 조회

    /**
     * 게시글 목록 조회
     * @return
     */
    public List<MemoEntity> findAll(){

        return this.memoRepository.findAll();
    }

    /**
     * 게시글 단건 조회
     * @param memoSeq
     * @return
     */
    public MemoEntity findMemo(Integer memoSeq) {
        Optional<MemoEntity> memoItem = this.memoRepository.findById(memoSeq);
        MemoEntity resultItem = memoItem.get();

        return resultItem;
    }

    //#endregion

    //#region - 저장

    /**
     * 게시글 저장
     * @param memoEntity
     * @return
     */
    public void saveMemo(MemoEntity memoEntity) {
        // TODO memoEntity Validation
        memoEntity.setCreatedBy(memoEntity.getMemoWriter());
        memoEntity.setModifiedBy(memoEntity.getMemoWriter());

        if(ObjectUtils.isEmpty(memoEntity) == false) {
            MemoEntity result = this.memoRepository.saveAndFlush(memoEntity);
        } else {
            // TODO Exception
        }
    }

    /**
     * 게시글 수정
     * @param memoEntity
     */
    public Boolean updateMemo(MemoEntity memoEntity) {
        Boolean result = false;
        memoEntity.setModifiedBy(memoEntity.getMemoWriter());
        MemoEntity saved = this.memoRepository.saveAndFlush(memoEntity);
        result = ObjectUtils.isEmpty(saved) == false;
        return result;
    }

    //#endregion

    //#region - 삭제

    public void deleteMemo(Integer memoSeq) {
        if(ObjectUtils.isEmpty(memoSeq) == false) {
            this.memoRepository.deleteById(memoSeq);
        }
    }

    //#endregion
}
