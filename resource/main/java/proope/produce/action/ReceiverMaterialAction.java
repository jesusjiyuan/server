package proope.produce.action;

import java.util.List;

import proope.produce.service.ReceiverMaterialService;
import proope.produce.to.entity.ReceiveMaterialDetailHistoryEntity;
import proope.produce.to.entity.ReceiveMaterialHistoryEntity;
import proope.produce.to.result.ReceiveMaterialHistoryStorePageResult;
import common.struts.BaseAction;

public class ReceiverMaterialAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ReceiverMaterialService receiverMaterialService;

	/* 领料历史 */
	private ReceiveMaterialHistoryStorePageResult receiveMaterialHistoryStorePageResult;
	
	private String bmmc;  //  部门名称
	
	/* 领料历史 */
	private ReceiveMaterialHistoryEntity receiveMaterialHistoryEntity;
	
	private String receiveMaterialHistoryId;  //  领料历史ID
	
	/* 领料历史明细 */
	private List<ReceiveMaterialDetailHistoryEntity> receiveMaterialDetailHistoryEntityList;
	
	/**
	 * 查询领料记录
	 * @return
	 */
	public String findReceiveMaterialHistoryStorePageResult(){
		receiveMaterialHistoryStorePageResult = receiverMaterialService.findReceiveMaterialHistoryStorePageResult(bmmc, getStart(), getLimit());
		return "receiveMaterialHistoryStorePageResult";
	}
	
	/**
	 * 创建领料记录
	 * @return
	 */
	public String createReceiveMaterialHistory(){
		return returnResponseWrite(receiverMaterialService.createReceiveMaterialHistoryEntity(receiveMaterialHistoryEntity, 
				receiveMaterialDetailHistoryEntityList, this.findSessionUserId()), "创建领料记录失败。");
	}
	
	public String findReceiveMaterialDetailHistoryListByRmhId(){
		receiveMaterialDetailHistoryEntityList = receiverMaterialService.findReceiveMaterialDetailHistoryListByRmhId(receiveMaterialHistoryId);
		return "receiveMaterialDetailHistoryEntityList";
	}
	
	//  -------------------------  get set --------------------------------
	public void setReceiverMaterialService(ReceiverMaterialService receiverMaterialService) {
		this.receiverMaterialService = receiverMaterialService;
	}
	public ReceiveMaterialHistoryStorePageResult getReceiveMaterialHistoryStorePageResult() {
		return receiveMaterialHistoryStorePageResult;
	}
	public void setReceiveMaterialHistoryStorePageResult(
			ReceiveMaterialHistoryStorePageResult receiveMaterialHistoryStorePageResult) {
		this.receiveMaterialHistoryStorePageResult = receiveMaterialHistoryStorePageResult;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public ReceiveMaterialHistoryEntity getReceiveMaterialHistoryEntity() {
		return receiveMaterialHistoryEntity;
	}
	public void setReceiveMaterialHistoryEntity(ReceiveMaterialHistoryEntity receiveMaterialHistoryEntity) {
		this.receiveMaterialHistoryEntity = receiveMaterialHistoryEntity;
	}
	public List<ReceiveMaterialDetailHistoryEntity> getReceiveMaterialDetailHistoryEntityList() {
		return receiveMaterialDetailHistoryEntityList;
	}
	public void setReceiveMaterialDetailHistoryEntityList(List<ReceiveMaterialDetailHistoryEntity> receiveMaterialDetailHistoryEntityList) {
		this.receiveMaterialDetailHistoryEntityList = receiveMaterialDetailHistoryEntityList;
	}
	public String getReceiveMaterialHistoryId() {
		return receiveMaterialHistoryId;
	}
	public void setReceiveMaterialHistoryId(String receiveMaterialHistoryId) {
		this.receiveMaterialHistoryId = receiveMaterialHistoryId;
	}
	
}
