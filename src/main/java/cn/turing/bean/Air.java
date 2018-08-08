package cn.turing.bean;

import java.io.Serializable;



public class Air implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float WD;
	private String Type;
	private String Mark;
	private Integer Node;
	private Integer YW;
	private float SD;
	private PMS200C pms200c;
	public double getWD() {
		return WD;
	}
	public void setWD(float wD) {
		WD = wD;
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
	public Integer getYW() {
		return YW;
	}
	public void setYW(Integer yW) {
		YW = yW;
	}
	public double getSD() {
		return SD;
	}
	public void setSD(float sD) {
		SD = sD;
	}
	public PMS200C getPms200c() {
		return pms200c;
	}
	public void setPms200c(PMS200C pms200c) {
		this.pms200c = pms200c;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Mark == null) ? 0 : Mark.hashCode());
		result = prime * result + ((Node == null) ? 0 : Node.hashCode());
		long temp;
		temp = Double.doubleToLongBits(SD);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((Type == null) ? 0 : Type.hashCode());
		temp = Double.doubleToLongBits(WD);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((YW == null) ? 0 : YW.hashCode());
		result = prime * result + ((pms200c == null) ? 0 : pms200c.hashCode());
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
		Air other = (Air) obj;
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
		if (Double.doubleToLongBits(SD) != Double.doubleToLongBits(other.SD))
			return false;
		if (Type == null) {
			if (other.Type != null)
				return false;
		} else if (!Type.equals(other.Type))
			return false;
		if (Double.doubleToLongBits(WD) != Double.doubleToLongBits(other.WD))
			return false;
		if (YW == null) {
			if (other.YW != null)
				return false;
		} else if (!YW.equals(other.YW))
			return false;
		if (pms200c == null) {
			if (other.pms200c != null)
				return false;
		} else if (!pms200c.equals(other.pms200c))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Air [WD=" + WD + ", Type=" + Type + ", Mark=" + Mark + ", Node=" + Node + ", YW=" + YW + ", SD=" + SD
				+ ", pms200c=" + pms200c + "]";
	}
	public Air(float wD, String type, String mark, Integer node, Integer yW, float sD, PMS200C pms200c) {
		super();
		WD = wD;
		Type = type;
		Mark = mark;
		Node = node;
		YW = yW;
		SD = sD;
		this.pms200c = pms200c;
	}
	public Air() {
		super();
	}
	
}

