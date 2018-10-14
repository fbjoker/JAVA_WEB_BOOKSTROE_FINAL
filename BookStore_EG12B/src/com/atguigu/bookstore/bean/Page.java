package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable{
	/*
	 * index 分页图书集合的查询起始索引 、 size 每页显示记录条数 、data每页的图书集合 、 pageNumber 当前分页的页面、
	 * totalCount 图书的总记录条数、totalPage 分页的总页码 - 实现分页功能
	 */
	private List<T> data;
	private int index;
	private int size;
	private int pageNumber;
	private int totalCount;
	private int totalPage;
	private String path;

	public Page() {
		super();
	}

	public Page(List<T> data, int index, int size, int pageNumber, int totalCount, int totalPage, String path) {
		super();
		this.data = data;
		this.index = index;
		this.size = size;
		this.pageNumber = pageNumber;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.path = path;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getIndex() {
		index = (getPageNumber()-1)*getSize();
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPageNumber() {
		if(pageNumber<1) {
			pageNumber =  1;
		}else if( pageNumber> getTotalPage()) {
			pageNumber = getTotalPage();
		}
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		
		if(getTotalCount()%getSize()==0) {
			totalPage=getTotalCount()/getSize();
		}else {
			totalPage=getTotalCount()/getSize()+1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Page [data=" + data + ", index=" + index + ", size=" + size + ", pageNumber=" + pageNumber
				+ ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", path=" + path + "]";
	}

}
