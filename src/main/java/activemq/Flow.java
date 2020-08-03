package activemq;

import java.io.Serializable;

public class Flow implements Serializable{
	public Flow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flow(String flowId, String flowName, Integer flowBusiId) {
		super();
		this.flowId = flowId;
		this.flowName = flowName;
		this.flowBusiId = flowBusiId;
	}

	private String flowId;
	private String flowName;
	private Integer flowBusiId;

	public Integer getFlowBusiId() {
		return flowBusiId;
	}

	public void setFlowBusiId(Integer flowBusiId) {
		this.flowBusiId = flowBusiId;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	@Override
	public String toString() {
		return "Flow [flowId=" + flowId + ", flowName=" + flowName
				+ ", flowBusiId=" + flowBusiId + "]";
	}

}
