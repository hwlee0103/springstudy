package com.example.springstudy.service.memo;

import com.example.springstudy.entity.memo.MemoEntity;
import com.example.springstudy.repository.memo.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

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

    //#endregion

    //#region - 저장

    /**
     * 게시글 저장
     * @param memoEntity
     * @return
     */
    public void saveMemo(MemoEntity memoEntity) {
        // TODO memoEntity Validation
        if(ObjectUtils.isEmpty(memoEntity) == false) {
            MemoEntity result = this.memoRepository.saveAndFlush(memoEntity);
        } else {
            // TODO Exception
        }
    }

    //#endregion


}
