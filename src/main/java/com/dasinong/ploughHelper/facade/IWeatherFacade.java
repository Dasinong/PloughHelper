package com.dasinong.ploughHelper.facade;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface IWeatherFacade {

	public abstract Object getWeather(double lat, double lon) throws Exception;

	public abstract Object getWeather(Long areaId, double lat, double lon);

	public abstract Object getWeather(Long areaId);
}