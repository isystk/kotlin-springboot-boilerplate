package com.isystk.sample.batch.jobs.importMst;

import static com.isystk.sample.common.util.ValidateUtils.isNotEmpty;

import com.isystk.sample.batch.context.BatchContext;
import com.isystk.sample.batch.context.BatchContextHolder;
import com.isystk.sample.batch.item.ItemError;
import com.isystk.sample.batch.jobs.BaseTasklet;
import com.isystk.sample.common.util.DateUtils;
import com.isystk.sample.common.util.ObjectMapperUtils;
import com.isystk.sample.domain.dao.MPostTagDao;
import com.isystk.sample.domain.entity.MPostTag;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.ValidationException;
import lombok.val;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ユーザー情報取り込みタスク
 */
public class ImportMstPostTasklet extends BaseTasklet<ImportMstPostDto> {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImportMstPostTasklet.class);
  @Autowired
  MPostTagDao mPostTagDao;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws IOException {
    RepeatStatus status = super.execute(contribution, chunkContext);

    BatchContext context = BatchContextHolder.getContext();
    List<ItemError> errors = getErrors(context);

    if (isNotEmpty(errors)) {
      errors.forEach(e -> {
        String sourceName = e.getSourceName();
        int position = e.getPosition();
        String errorMessage = e.getErrorMessage();
        log.error("エラーがあります。ファイル名={}, 行数={}, エラーメッセージ={}", sourceName, position, errorMessage);
      });

      throw new ValidationException("取り込むファイルに不正な行が含まれています。");
    }

    return status;
  }

  @Override
  protected void doProcess(BatchContext context) {

    Path path = Paths.get("src/main/resources/tag_mst.csv");
    List importTagDtoList = Lists.newArrayList();
    try {
      List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
      lines.forEach(line -> {
        String[] row = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, ",");
        ImportMstPostDto dto = new ImportMstPostDto();
        dto.setSourceName(path.toString());
        dto.setPostTagId(row[0]);
        dto.setName(row[1]);
        importTagDtoList.add(dto);
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    MPostTag[] csvPostTags = ObjectMapperUtils.map(importTagDtoList, MPostTag[].class);

    for (MPostTag csvPostTag : csvPostTags) {
      var data = mPostTagDao.selectById(csvPostTag.getPostTagId());
      if (data.isEmpty()) {
        MPostTag mPostTag = ObjectMapperUtils.map(csvPostTag, MPostTag.class);
        mPostTag.setRegistTime(DateUtils.getNow());
        mPostTag.setUpdateTime(DateUtils.getNow());
        mPostTag.setDeleteFlg(false);
        mPostTagDao.insert(mPostTag);
      } else {
        MPostTag mPostTag = data.get();
        mPostTag.setName(csvPostTag.getName());
        mPostTag.setUpdateTime(DateUtils.getNow());
        mPostTagDao.update(mPostTag);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private List<ItemError> getErrors(BatchContext context) {
    Map<String, Object> additionalInfo = context.getAdditionalInfo();
    List<ItemError> errors = (List<ItemError>) additionalInfo.get("errors");

    if (errors == null) {
      errors = new ArrayList<>();
    }

    return errors;
  }

}
