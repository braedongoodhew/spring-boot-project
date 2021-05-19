package ca.sheridancollege.goodhewb.beans;

import lombok.Data;


@Data
public class Volume {

	private Long id;
	private int width;
	private int height;
	private int depth;
	private int result;

	public Volume() {
		this.width = 0;
		this.height = 0;
		this.depth = 0;
		this.result = this.height * this.width * this.depth;

	}

	public Volume(int height, int width, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.result = this.height * this.width * this.depth;
	}

	public void setResult() {
		this.result = this.width * this.depth * this.height;
	}

}
