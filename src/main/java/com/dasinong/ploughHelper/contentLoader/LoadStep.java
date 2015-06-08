package com.dasinong.ploughHelper.contentLoader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.CropDao;
import com.dasinong.ploughHelper.dao.SubStageDao;
import com.dasinong.ploughHelper.dao.TaskSpecDao;
import com.dasinong.ploughHelper.dao.VarietyDao;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Step;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.TaskSpec;
import com.dasinong.ploughHelper.model.Variety;

import au.com.bytecode.opencsv.CSVReader;

public class LoadStep {
	public final File FILEFOLDER = new File(new File("C:/Users/Jason Wu/workspace/PloughHelper").getAbsoluteFile(), "sourcefiles");
	public final File FILE_STAGE = new File(FILEFOLDER, "stage.csv");
	public final File FILE_STEP = new File(FILEFOLDER, "task_step.csv");
	public Map<Long, SubStage> subStageMap = new HashMap<Long, SubStage>();
	public Map<Long, TaskSpec> taskSpecMap = new HashMap<Long, TaskSpec>();

	@Test
	public void test(){
		
	}
	
	public void readFile() throws IOException {
		SubStageDao subStageDao = (SubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao");
		TaskSpecDao taskSpecDao = (TaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		
		CSVReader reader = new CSVReader(new FileReader(FILE_STAGE), ',', '\"',1);  // first line is title
		List entries = reader.readAll();
		for (int i = 0; i < entries.size(); i++) {
			// create a subStage object for each entry
			String items[] = (String[]) entries.get(i);
			SubStage subStage = generateSubStage(items);
			subStageDao.save(subStage);
		}
		// subStageMap 构造完成
		// All subStages have been saved to database, correctly linked to their corresponding steps
		
		reader = new CSVReader(new FileReader(FILE_STEP), ',', '\"',1);  // first line is title
		entries = reader.readAll();
		for (int i = 0; i < entries.size(); i++) {
			// create a subStage object for each entry
			String items[] = (String[]) entries.get(i);
			TaskSpec taskSpec = generateTaskSpec(items);
			if (!taskSpecMap.containsKey(taskSpec.getTaskSpecId())){
				taskSpecMap.put(taskSpec.getTaskSpecId(), taskSpec); // add to taskSpecMap for constructing steps
			}
			generateStep(items);
		}
		for (Long key: taskSpecMap.keySet()) {
			TaskSpec taskSpec = taskSpecMap.get(key);
			List<Step> steps = taskSpec.getSteps();
			taskSpec.setSteps(steps);
			taskSpecDao.save(taskSpec);
		}
	}
	
	public TaskSpec generateTaskSpec(String[] items){
		TaskSpec taskSpec = null;
		Long taskSpecId = Long.parseLong(items[0]);
		Long subStageId = Long.parseLong(items[3]);
		
		SubStage subStage = subStageMap.get(subStageId);
		taskSpec = new TaskSpec(items[1], subStage, items[2]);
		taskSpec.setTaskSpecId(taskSpecId);
		
		return taskSpec;
	}
	
	public void generateStep(String[] items){
		Long taskSpecId = Long.parseLong(items[0]);
		TaskSpec taskSpec = taskSpecMap.get(taskSpecId);
		String regions[] = items[4].split("，");
		
		for (int i = 0; i < regions.length; i++) {
			Step step = new Step(items[6], taskSpec);
			step.setFitRegion(regions[i].trim());
			step.setDescription(items[7]);
			step.setPicture(items[8]);
			
			taskSpec.getSteps().add(step);
		}
	}
	
	public SubStage generateSubStage(String[] items){
		SubStage subStage = new SubStage(items[1], items[2]);
		subStage.setSubStageId(Long.parseLong(items[0]));
		if (!items[3].trim().equals("")) {
			subStage.setReqMinTemp(Integer.parseInt(items[3].trim()));
		}
		if (!items[4].trim().equals("")) {
			subStage.setReqAvgTemp(Integer.parseInt(items[4].trim()));
		}
		if (!items[8].trim().equals("")) {
			String duration = items[8].trim();
			String durations[] = duration.split("，");
			double durationLow = (new Double(durations[0].trim().replace("%", "")));
			subStage.setDurationLow(durationLow);
			double durationMid = (new Double(durations[1].trim().replace("%", "")));
			subStage.setDurationMid(durationMid);
			double durationHigh = (new Double(durations[2].trim().replace("%", "")));
			subStage.setDurationHigh(durationHigh);
		}
		subStageMap.put(subStage.getSubStageId(), subStage); // add to subStageMap for future use
		return subStage;
	}
}
