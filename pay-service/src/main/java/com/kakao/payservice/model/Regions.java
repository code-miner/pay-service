package com.kakao.payservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Regions {
	@Id
	@GeneratedValue
	private Long code;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String name;
}
