package tramas.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.scene.image.Image;

public class ImageAdapter extends XmlAdapter<String, Image> {

	@Override
	public String marshal(Image v) throws Exception {
		if (v == null) return null;
		return ImageUtils.encodeImage(v);
	}

	@Override
	public Image unmarshal(String v) throws Exception {
		if (v == null) return null;
		return ImageUtils.decodeImage(v);
	}

}
