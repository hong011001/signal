
/**
 * ���˻���Ϣģ��
 * @author hong
 */
public class Message {
	private String id;//���˻�id
	private Integer lastX;//ǰһ����ϢX����
	private Integer lastY;//ǰһ����ϢY����
	private Integer lastZ;//ǰһ����ϢZ����
	private Integer offsetX;//ƫ����X
	private Integer offsetY;//ƫ����Y
	private Integer offsetZ;//ƫ����Z
	private Integer currentX;//��ǰX����
	private Integer currentY;//��ǰY����
	private Integer currentZ;//��ǰZ����
	private boolean crash = true;//�Ƿ���ϣ�Ĭ�Ϲ��ϣ���У����Ϣʱ������״̬���
	
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
