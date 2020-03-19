package cn.treeshell.recruit.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: panjing
 * @Date: 2020/3/18 22:45
 */
@Data
@Entity
@Table(name="enterprise")
public class Enterprise implements Serializable{

	@Id
	private String id;
	// 企业名称
	private String name;
	// 企业简介
	private String summary;
	// 企业地址
	private String address;
	// 标签列表
	private String labels;
	// 坐标
	private String coordinate;
	// 是否热门
	private String ishot;
	private String logo;
	// 职位数
	private Integer jobcount;
	private String url;
}
