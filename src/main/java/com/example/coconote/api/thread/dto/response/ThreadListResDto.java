package com.example.coconote.api.thread.dto.response;

import com.example.coconote.api.tag.dto.response.TagResDto;
import com.example.coconote.api.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThreadListResDto {
    private String image;
    private String memberName;
    private LocalDateTime createdTime;
    private String content;
    private List<String> files;
    private List<ThreadListResDto> childThreads;
    //TODO: 태그 추가해줘야됨
    private List<TagResDto> tags;
}
