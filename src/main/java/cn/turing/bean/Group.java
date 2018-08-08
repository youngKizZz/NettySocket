package cn.turing.bean;

import java.io.Serializable;
import java.util.Date;



public class Group implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer volt;
	private String Type;
	private String Mark;
	private Integer Node;
	private Integer Person;
	private Date addTime;
	public Integer getVolt() {
		return volt;
	}
	public void setVolt(Integer volt) {
		this.volt = volt;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getMark() {
		return Mark;
	}
	public void setMark(String mark) {
		Mark = mark;
	}
	public Integer getNode() {
		return Node;
	}
	public void setNode(Integer node) {
		Node = node;
	}
	public Integer getPerson() {
		return Person;
	}
	public void setPerson(Integer person) {
		Person = person;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Mark == null) ? 0 : Mark.hashCode());
		result = prime * result + ((Node == null) ? 0 : Node.hashCode());
		result = prime * result + ((Person == null) ? 0 : Person.hashCode());
		result = prime * result + ((Type == null) ? 0 : Type.hashCode());
		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
		result = prime * result + ((volt == null) ? 0 : volt.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (Mark == null) {
			if (other.Mark != null)
				return false;
		} else if (!Mark.equals(other.Mark))
			return false;
		if (Node == null) {
			if (other.Node != null)
				return false;
		} else if (!Node.equals(other.Node))
			return false;
		if (Person == null) {
			if (other.Person != null)
				return false;
		} else if (!Person.equals(other.Person))
			return false;
		if (Type == null) {
			if (other.Type != null)
				return false;
		} else if (!Type.equals(other.Type))
			return false;
		if (addTime == null) {
			if (other.addTime != null)
				return false;
		} else if (!addTime.equals(other.addTime))
			return false;
		if (volt == null) {
			if (other.volt != null)
				return false;
		} else if (!volt.equals(other.volt))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Group [volt=" + volt + ", Type=" + Type + ", Mark=" + Mark + ", Node=" + Node + ", Person=" + Person
				+ ", addTime=" + addTime + "]";
	}
	public Group(Integer volt, String type, String mark, Integer node, Integer person, Date addTime) {
		super();
		this.volt = volt;
		Type = type;
		Mark = mark;
		Node = node;
		Person = person;
		this.addTime = addTime;
	}
	public Group() {
		super();
	}
	
}

