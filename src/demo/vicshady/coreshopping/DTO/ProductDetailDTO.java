package demo.vicshady.coreshopping.DTO;

public class ProductDetailDTO {
	public String pdtId;
	public String versionId;
	public String pdtName;
	public String isFeatured;
	public String pdtFeaturedPrice;
	public String pdtPrice;
	public String isStockout;
	public String isLimited;
	public String pdtBrand;
	public String pdtDesc;
	public String pdtType;
	public String isFoodCoupon;
	public String stockAvailable;
	public String imagePath;
	public String getPdtId() {
		return pdtId;
	}
	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getPdtName() {
		return pdtName;
	}
	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}
	public String getIsFeatured() {
		return isFeatured;
	}
	public void setIsFeatured(String isFeatured) {
		this.isFeatured = isFeatured;
	}
	public String getPdtFeaturedPrice() {
		return pdtFeaturedPrice;
	}
	public void setPdtFeaturedPrice(String pdtFeaturedPrice) {
		this.pdtFeaturedPrice = pdtFeaturedPrice;
	}
	public String getPdtPrice() {
		return pdtPrice;
	}
	public void setPdtPrice(String pdtPrice) {
		this.pdtPrice = pdtPrice;
	}
	public String getIsStockout() {
		return isStockout;
	}
	public void setIsStockout(String isStockout) {
		this.isStockout = isStockout;
	}
	public String getIsLimited() {
		return isLimited;
	}
	public void setIsLimited(String isLimited) {
		this.isLimited = isLimited;
	}
	public String getPdtBrand() {
		return pdtBrand;
	}
	public void setPdtBrand(String pdtBrand) {
		this.pdtBrand = pdtBrand;
	}
	public String getPdtDesc() {
		return pdtDesc;
	}
	public void setPdtDesc(String pdtDesc) {
		this.pdtDesc = pdtDesc;
	}
	public String getPdtType() {
		return pdtType;
	}
	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}
	public String getIsFoodCoupon() {
		return isFoodCoupon;
	}
	public void setIsFoodCoupon(String isFoodCoupon) {
		this.isFoodCoupon = isFoodCoupon;
	}
	public String getStockAvailable() {
		return stockAvailable;
	}
	public void setStockAvailable(String stockAvailable) {
		this.stockAvailable = stockAvailable;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	public ProductDetailDTO(String pdtId, String versionId, String pdtName,
			String isFeatured, String pdtFeaturedPrice, String pdtPrice,
			String isStockout, String isLimited, String pdtBrand,
			String pdtDesc, String pdtType, String isFoodCoupon,
			String stockAvailable, String imagePath) {
		super();
		this.pdtId = pdtId;
		this.versionId = versionId;
		this.pdtName = pdtName;
		this.isFeatured = isFeatured;
		this.pdtFeaturedPrice = pdtFeaturedPrice;
		this.pdtPrice = pdtPrice;
		this.isStockout = isStockout;
		this.isLimited = isLimited;
		this.pdtBrand = pdtBrand;
		this.pdtDesc = pdtDesc;
		this.pdtType = pdtType;
		this.isFoodCoupon = isFoodCoupon;
		this.stockAvailable = stockAvailable;
		this.imagePath = imagePath;
	}
	public ProductDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
