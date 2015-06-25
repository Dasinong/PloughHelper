package com.dasinong.ploughHelper.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class FullTextSearch {
	private final int NUM = 30; // max search result items
	private final int HIGHLIGHT_LEN = 20; // highlight content length
	private SimpleHTMLFormatter highlightFormatter;
	private String path;
	
	public FullTextSearch(String path){
		this.path = path;
		this.highlightFormatter = null;
	}
	
	private IndexWriter getIndexWriter(Directory dir, Analyzer analyzer) throws IOException{
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		return new IndexWriter(dir, config);
	}
	
	private void createIndex(){
		Analyzer analyzer = new IKAnalyzer(true);
		Directory directory = null;
		IndexWriter iwriter = null;
		
		try {
			directory = FSDirectory.open(new File(this.path));
			iwriter = getIndexWriter(directory, analyzer);
		} catch (IOException e) {
			System.out.println("open index file failed");
			e.printStackTrace();
		}
		
		try {
			Document doc1 = new Document();
			doc1.add(new TextField("title", "水稻种植", Store.YES));
			doc1.add(new TextField("content", "水稻是一种粮食，因为数据立方体和量化属性都可以利用概念分层。第二，可以挖掘量化关联规则，其中量化属性根据分箱和/或聚类动态离散化，“邻近的”关联规则可以用聚类合并，产生更简洁、更有意义的规则。基于约束的规则挖掘允许用户通过提供元规则（即模式模板）和其他挖掘约束对规则搜索聚焦。这种挖掘推动了说明性数据挖掘查询语言和用户界面的使用，并对挖掘查询优化提出了巨大挑战。规则约束可以分为五类：反单调的、单调的、简洁的、可转变的和不可转变的。前四类约束可以在频繁项集挖掘中使用，使挖掘更有功效，更有效率。没有进一步分析或领域知识，关联规则不应该直接用于预测。它们不必指示因果关系。然而，对于进一步探查，它们是有帮助的切入点，使得它们成为理解数据的流行工具。流数据不断地在计算机系统中流进流出并且具有变化的更新速度，涉及数据流的应用非常广泛。大纲提供数据流的汇总，通常用来返回查询的近似解答。随机抽样、滑动窗口、直方图、多分辨", Store.YES));
			iwriter.addDocument(doc1);
			
			Document doc2 = new Document();
			doc2.add(new TextField("title", "小麦栽培", Store.YES));
			doc2.add(new TextField("content", "小麦是一种粮食，它和水稻不一样", Store.YES));
			iwriter.addDocument(doc2);
		} catch (IOException e) {
			System.out.println("create index failed");
			e.printStackTrace();
		}
		
		if(iwriter != null){
			try {
				iwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public HashMap[] search(String keyword, String[] searchFields, String[] resultFields) throws ParseException, IOException, InvalidTokenOffsetsException{
		HashMap[] result = new HashMap[NUM];
		
		Analyzer analyzer = new IKAnalyzer(true);
		Directory directory = null;
		IndexReader ireader = null;
		IndexSearcher isearcher = null;
		
		try {
			directory = FSDirectory.open(new File(this.path));
			ireader = IndexReader.open(directory);
		} catch (IOException e) {
			System.out.println("search -- open index file failed");
			e.printStackTrace();
		}
		isearcher = new IndexSearcher(ireader);

		QueryParser qp = new MultiFieldQueryParser(Version.LUCENE_46, searchFields, analyzer);
		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query query = qp.parse(keyword);
		
		TopDocs topDocs = isearcher.search(query, NUM);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for(int i = 0; i < topDocs.totalHits; i++){
			Document targetDoc = isearcher.doc(scoreDocs[i].doc);
			result[i] = new HashMap();
			for(int j = 0; j < resultFields.length; j++){
				if(this.highlightFormatter != null){
					Highlighter highlighter = new Highlighter(this.highlightFormatter, new QueryScorer(query));
					highlighter.setTextFragmenter(new SimpleFragmenter(HIGHLIGHT_LEN));
					TokenStream tokenStream1 = analyzer.tokenStream(resultFields[j], new StringReader(targetDoc.get(resultFields[j])));
					String _result = highlighter.getBestFragment(tokenStream1, targetDoc.get(resultFields[j]));
					if(_result == null){ // not highlight the field which doesn't contain the keyword
						_result = targetDoc.get(resultFields[j]);
					}
					result[i].put(resultFields[j], _result);
				}else{
					result[i].put(resultFields[j], targetDoc.get(resultFields[j]));	
				}
			}
		}
		return result;
	}
	
	public void setHighlighterFormatter(String highLightPrefix, String highLightSuffix){
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(highLightPrefix, highLightSuffix);
		this.highlightFormatter = simpleHTMLFormatter;
	}

	public static void main(String args[]){
		FullTextSearch bs = new FullTextSearch("E:/index");
		bs.setHighlighterFormatter("<font color='red'>", "</font>");
		//bs.createIndex(); // only need create index once
		String[] a = {"title", "content"};
		String[] b = {"title", "content"};
		try {
			HashMap[] h = bs.search("粮食", a, b);
			System.out.println(h.length);
			for(int k = 0; k < h.length; k++){
				if(h[k] == null){
					break;
				}
				System.out.println(h[k].toString());
			}
		} catch (ParseException | IOException | InvalidTokenOffsetsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}