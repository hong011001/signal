
/**
 * 无人机消息模型
 * @author hong
 */
public class Message {
	private String id;//无人机id
	private Integer lastX;//前一条消息X坐标
	private Integer lastY;//前一条消息Y坐标
	private Integer lastZ;//前一条消息Z坐标
	private Integer offsetX;//偏移量X
	private Integer offsetY;//偏移量Y
	private Integer offsetZ;//偏移量Z
	private Integer currentX;//当前X坐标
	private Integer currentY;//当前Y坐标
	private Integer currentZ;//当前Z坐标
	private boolean crash = true;//是否故障，默认故障，在校验消息时，再作状态变更
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getLastX() {
		return lastX;
	}
	public void setLastX(Integer lastX) {
		this.lastX = lastX;
	}
	public Integer getLastY() {
		return lastY;
	}
	public void setLastY(Integer lastY) {
		this.lastY = lastY;
	}
	public Integer getLastZ() {
		return lastZ;
	}
	public void setLastZ(Integer lastZ) {
		this.lastZ = lastZ;
	}
	public Integer getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(Integer offsetX) {
		this.offsetX = offsetX;
	}
	public Integer getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(Integer offsetY) {
		this.offsetY = offsetY;
	}
	public Integer getOffsetZ() {
		return offsetZ;
	}
	public void setOffsetZ(Integer offsetZ) {
		this.offsetZ = offsetZ;
	}
	public Integer getCurrentX() {
		return currentX;
	}
	public void setCurrentX(Integer currentX) {
		this.currentX = currentX;
	}
	public Integer getCurrentY() {
		return currentY;
	}
	public void setCurrentY(Integer currentY) {
		this.currentY = currentY;
	}
	public Integer getCurrentZ() {
		return currentZ;
	}
	public void setCurrentZ(Integer currentZ) {
		this.currentZ = currentZ;
	}
	public boolean isCrash() {
		return crash;
	}
	public void setCrash(boolean crash) {
		this.crash = crash;
	}

}
