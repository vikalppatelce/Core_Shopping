package demo.vicshady.coreshopping.DTO;

public class SubCategoryDTO {
	
	
	public String catId;
	public String catName;
	public String parentId;
	public String imagePath;
	
	public SubCategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubCategoryDTO(String catId, String catName, String parentId,
			String imagePath) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.parentId = parentId;
		this.imagePath = imagePath;
	}

	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
