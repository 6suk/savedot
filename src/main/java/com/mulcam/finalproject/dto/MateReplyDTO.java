package com.mulcam.finalproject.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.entity.MateImg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MateReplyDTO {
	
	private Long rid; // 댓글 rid
	private Long uid; // --> user.fk
	private Long mid; // --> mate.fk
	private String nickname; // --> user.fk
	private String content; 
	private LocalDateTime regDate;
	private int grp; // 댓글이 속한 댓글번호
	private int grps; // 같은 grp 중에 순서
	private int grpl;  // 댓글의 깊이 댓글이면 0, 답글이면 1 

}
