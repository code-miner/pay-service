package com.kakao.payservice.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "REGIONS_DETAIL")
@Builder
public class RegionsDetail {// extends BaseTimeEntity {

	// @GeneratedValue
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", insertable = true, updatable = true, unique = true, nullable = false)
	private Long id;

	// FK
	@OneToOne
	@JoinColumn(name = "code", referencedColumnName = "code")
	private Regions regions;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String target;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String usage;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String strLimit;

	@Column(columnDefinition = "NUMBER", nullable = false)
	private Long numLimit;

	@Column(columnDefinition = "NUMBER", nullable = false)
	private Double numRate;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String rate;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String institute;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String mgmt;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String reception;

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@Column
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
}
