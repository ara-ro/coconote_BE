package com.example.coconote.api.thread.entity;

import com.example.coconote.api.channel.entity.Channel;
import com.example.coconote.api.member.entity.Member;
import com.example.coconote.api.tag.dto.response.TagResDto;
import com.example.coconote.api.thread.dto.response.ThreadListResDto;
import com.example.coconote.api.threadTag.entity.ThreadTag;
import com.example.coconote.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Thread extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="thread_id")
    private Long id;
    private String content;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> files;
    @ManyToOne(fetch = FetchType.LAZY)
    private Thread parent;
    //TODO:추후 워크스페이스-유저로 변경
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Channel channel;
    @Builder.Default
    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<ThreadTag> threadTags = new ArrayList<>();

    public ThreadListResDto fromEntity() {
        List<TagResDto> tags = this.threadTags.stream().map(threadTag -> threadTag.fromEntity()).toList();
        return ThreadListResDto.builder()
                .memberName(this.member.getNickname())
                .createdTime(this.getCreatedTime())
                .content(this.content)
                .files(this.files)
                .tags(tags)
                .build();
    }

    public ThreadListResDto fromEntity(List<ThreadListResDto> childThreadList) {
        List<TagResDto> tags = this.threadTags.stream().map(threadTag -> threadTag.fromEntity()).toList();
        return ThreadListResDto.builder()
                .memberName(this.member.getNickname())
                .createdTime(this.getCreatedTime())
                .content(this.content)
                .files(this.files)
                .childThreads(childThreadList)
                .tags(tags)
                .build();
    }
}
