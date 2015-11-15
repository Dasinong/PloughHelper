package com.dasinong.ploughHelper.modelTran;

import java.util.HashMap;
import java.util.Random;

public class Ads {
	private static Ads ads;

	public LaoNong getAds(Integer id) {
		return this.ad.get(id);
	}

	public LaoNong getRndAds() {
		Random rnd = new Random();
		int id = rnd.nextInt(this.ad.size());
		return this.ad.get(id);
	}

	public static Ads allAds() {
		if (ads == null)
			ads = new Ads();
		return ads;
	}

	private Ads() {
		ad = new HashMap<Integer, LaoNong>();
		loadContent();
	}

	private void loadContent() {
		LaoNong d = new LaoNong(1, 1, "banner1.png", "", "", "");
		ad.put(d.id, d);
		d = new LaoNong(2, 1, "banner2.png", "", "", "");
		ad.put(d.id, d);
		d = new LaoNong(3, 1, "banner3.png", "", "", "");
		ad.put(d.id, d);
	}

	public static void main(String[] args) {
		System.out.println(Ads.allAds().getRndAds().picName);
		System.out.println(Ads.allAds().getRndAds().picName);
		System.out.println(Ads.allAds().getRndAds().picName);
	}

	private HashMap<Integer, LaoNong> ad = new HashMap<Integer, LaoNong>();

}
