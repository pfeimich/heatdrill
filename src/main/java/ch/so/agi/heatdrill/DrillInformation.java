package ch.so.agi.heatdrill;

public class DrillInformation {
	private Integer requestId; 							
	private Boolean permitted;
	
	private Integer depth; 	
	
	private Boolean gwsZone;				
	private Boolean gwPresent;				
	private Boolean spring;
	private Boolean gwRoom;
	private Boolean wasteSite;
	private Boolean landslide;
	
	private String[] infoTextRows;
	
	public DrillInformation() {} //required for bean

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Boolean getPermitted() {
		return permitted;
	}

	public void setPermitted(Boolean permitted) {
		this.permitted = permitted;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Boolean getGwsZone() {
		return gwsZone;
	}

	public void setGwsZone(Boolean gwsZone) {
		this.gwsZone = gwsZone;
	}

	public Boolean getGwPresent() {
		return gwPresent;
	}

	public void setGwPresent(Boolean gwPresent) {
		this.gwPresent = gwPresent;
	}

	public Boolean getSpring() {
		return spring;
	}

	public void setSpring(Boolean spring) {
		this.spring = spring;
	}

	public Boolean getGwRoom() {
		return gwRoom;
	}

	public void setGwRoom(Boolean gwRoom) {
		this.gwRoom = gwRoom;
	}

	public Boolean getWasteSite() {
		return wasteSite;
	}

	public void setWasteSite(Boolean wasteSite) {
		this.wasteSite = wasteSite;
	}

	public String[] getInfoTextRows() {
		return infoTextRows;
	}

	public void setInfoText(String infoText) {
		String splitToken = "\n";
		this.infoTextRows = infoText.split(splitToken);
	}	
	
	public Boolean getLandslide() {
		return landslide;
	}

	public void setLandslide(Boolean landslide) {
		this.landslide = landslide;
	}
}
