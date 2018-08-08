package cn.turing.bean;

import java.io.Serializable;
import java.util.Date;



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
	private Integer cfpm10nd;
	private Integer gs5um;
	private Integer gs25um;
	private Integer cfpmnd10;
	private Integer gs10um;
	private Integer cfpm25nd;
	private Integer dqpm10nd;
	private Integer dqpm25nd;
	private Integer dqpmnd10;
	private Integer gs03um;
	private Integer gs05um;
	private Integer gs1um;
	private Date addTime;
	public float getWD() {
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
	public float getSD() {
		return SD;
	}
	public void setSD(float sD) {
		SD = sD;
	}
	public Integer getCfpm10nd() {
		return cfpm10nd;
	}
	public void setCfpm10nd(Integer cfpm10nd) {
		this.cfpm10nd = cfpm10nd;
	}
	public Integer getGs5um() {
		return gs5um;
	}
	public void setGs5um(Integer gs5um) {
		this.gs5um = gs5um;
	}
	public Integer getGs25um() {
		return gs25um;
	}
	public void setGs25um(Integer gs25um) {
		this.gs25um = gs25um;
	}
	public Integer getCfpmnd10() {
		return cfpmnd10;
	}
	public void setCfpmnd10(Integer cfpmnd10) {
		this.cfpmnd10 = cfpmnd10;
	}
	public Integer getGs10um() {
		return gs10um;
	}
	public void setGs10um(Integer gs10um) {
		this.gs10um = gs10um;
	}
	public Integer getCfpm25nd() {
		return cfpm25nd;
	}
	public void setCfpm25nd(Integer cfpm25nd) {
		this.cfpm25nd = cfpm25nd;
	}
	public Integer getDqpm10nd() {
		return dqpm10nd;
	}
	public void setDqpm10nd(Integer dqpm10nd) {
		this.dqpm10nd = dqpm10nd;
	}
	public Integer getDqpm25nd() {
		return dqpm25nd;
	}
	public void setDqpm25nd(Integer dqpm25nd) {
		this.dqpm25nd = dqpm25nd;
	}
	public Integer getDqpmnd10() {
		return dqpmnd10;
	}
	public void setDqpmnd10(Integer dqpmnd10) {
		this.dqpmnd10 = dqpmnd10;
	}
	public Integer getGs03um() {
		return gs03um;
	}
	public void setGs03um(Integer gs03um) {
		this.gs03um = gs03um;
	}
	public Integer getGs05um() {
		return gs05um;
	}
	public void setGs05um(Integer gs05um) {
		this.gs05um = gs05um;
	}
	public Integer getGs1um() {
		return gs1um;
	}
	public void setGs1um(Integer gs1um) {
		this.gs1um = gs1um;
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
		result = prime * result + Float.floatToIntBits(SD);
		result = prime * result + ((Type == null) ? 0 : Type.hashCode());
		result = prime * result + Float.floatToIntBits(WD);
		result = prime * result + ((YW == null) ? 0 : YW.hashCode());
		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
		result = prime * result + ((cfpm10nd == null) ? 0 : cfpm10nd.hashCode());
		result = prime * result + ((cfpm25nd == null) ? 0 : cfpm25nd.hashCode());
		result = prime * result + ((cfpmnd10 == null) ? 0 : cfpmnd10.hashCode());
		result = prime * result + ((dqpm10nd == null) ? 0 : dqpm10nd.hashCode());
		result = prime * result + ((dqpm25nd == null) ? 0 : dqpm25nd.hashCode());
		result = prime * result + ((dqpmnd10 == null) ? 0 : dqpmnd10.hashCode());
		result = prime * result + ((gs03um == null) ? 0 : gs03um.hashCode());
		result = prime * result + ((gs05um == null) ? 0 : gs05um.hashCode());
		result = prime * result + ((gs10um == null) ? 0 : gs10um.hashCode());
		result = prime * result + ((gs1um == null) ? 0 : gs1um.hashCode());
		result = prime * result + ((gs25um == null) ? 0 : gs25um.hashCode());
		result = prime * result + ((gs5um == null) ? 0 : gs5um.hashCode());
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
		if (Float.floatToIntBits(SD) != Float.floatToIntBits(other.SD))
			return false;
		if (Type == null) {
			if (other.Type != null)
				return false;
		} else if (!Type.equals(other.Type))
			return false;
		if (Float.floatToIntBits(WD) != Float.floatToIntBits(other.WD))
			return false;
		if (YW == null) {
			if (other.YW != null)
				return false;
		} else if (!YW.equals(other.YW))
			return false;
		if (addTime == null) {
			if (other.addTime != null)
				return false;
		} else if (!addTime.equals(other.addTime))
			return false;
		if (cfpm10nd == null) {
			if (other.cfpm10nd != null)
				return false;
		} else if (!cfpm10nd.equals(other.cfpm10nd))
			return false;
		if (cfpm25nd == null) {
			if (other.cfpm25nd != null)
				return false;
		} else if (!cfpm25nd.equals(other.cfpm25nd))
			return false;
		if (cfpmnd10 == null) {
			if (other.cfpmnd10 != null)
				return false;
		} else if (!cfpmnd10.equals(other.cfpmnd10))
			return false;
		if (dqpm10nd == null) {
			if (other.dqpm10nd != null)
				return false;
		} else if (!dqpm10nd.equals(other.dqpm10nd))
			return false;
		if (dqpm25nd == null) {
			if (other.dqpm25nd != null)
				return false;
		} else if (!dqpm25nd.equals(other.dqpm25nd))
			return false;
		if (dqpmnd10 == null) {
			if (other.dqpmnd10 != null)
				return false;
		} else if (!dqpmnd10.equals(other.dqpmnd10))
			return false;
		if (gs03um == null) {
			if (other.gs03um != null)
				return false;
		} else if (!gs03um.equals(other.gs03um))
			return false;
		if (gs05um == null) {
			if (other.gs05um != null)
				return false;
		} else if (!gs05um.equals(other.gs05um))
			return false;
		if (gs10um == null) {
			if (other.gs10um != null)
				return false;
		} else if (!gs10um.equals(other.gs10um))
			return false;
		if (gs1um == null) {
			if (other.gs1um != null)
				return false;
		} else if (!gs1um.equals(other.gs1um))
			return false;
		if (gs25um == null) {
			if (other.gs25um != null)
				return false;
		} else if (!gs25um.equals(other.gs25um))
			return false;
		if (gs5um == null) {
			if (other.gs5um != null)
				return false;
		} else if (!gs5um.equals(other.gs5um))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Air [WD=" + WD + ", Type=" + Type + ", Mark=" + Mark + ", Node=" + Node + ", YW=" + YW + ", SD=" + SD
				+ ", cfpm10nd=" + cfpm10nd + ", gs5um=" + gs5um + ", gs25um=" + gs25um + ", cfpmnd10=" + cfpmnd10
				+ ", gs10um=" + gs10um + ", cfpm25nd=" + cfpm25nd + ", dqpm10nd=" + dqpm10nd + ", dqpm25nd=" + dqpm25nd
				+ ", dqpmnd10=" + dqpmnd10 + ", gs03um=" + gs03um + ", gs05um=" + gs05um + ", gs1um=" + gs1um
				+ ", addTime=" + addTime + "]";
	}
	public Air(float wD, String type, String mark, Integer node, Integer yW, float sD, Integer cfpm10nd, Integer gs5um,
			Integer gs25um, Integer cfpmnd10, Integer gs10um, Integer cfpm25nd, Integer dqpm10nd, Integer dqpm25nd,
			Integer dqpmnd10, Integer gs03um, Integer gs05um, Integer gs1um, Date addTime) {
		super();
		WD = wD;
		Type = type;
		Mark = mark;
		Node = node;
		YW = yW;
		SD = sD;
		this.cfpm10nd = cfpm10nd;
		this.gs5um = gs5um;
		this.gs25um = gs25um;
		this.cfpmnd10 = cfpmnd10;
		this.gs10um = gs10um;
		this.cfpm25nd = cfpm25nd;
		this.dqpm10nd = dqpm10nd;
		this.dqpm25nd = dqpm25nd;
		this.dqpmnd10 = dqpmnd10;
		this.gs03um = gs03um;
		this.gs05um = gs05um;
		this.gs1um = gs1um;
		this.addTime = addTime;
	}
	public Air() {
		super();
	}
	
	
}

