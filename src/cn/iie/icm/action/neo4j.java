package cn.iie.icm.action;
import cn.iie.icm.pojo.*;
import com.esen.util.StringMap;
import org.apache.commons.lang.ObjectUtils;
import org.neo4j.jdbc.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//引用配置文件类
//Class.forName("org.neo4j.jdbc.Driver");

//Connect
@Controller
@RequestMapping("/Knowledge")
public class neo4j{
    @RequestMapping("/tocorrelation")
    private ModelAndView tocorrelation(HttpServletRequest request, HttpServletResponse response,
                                   String targetUrl) throws IOException {
        return new ModelAndView("correlation/correlation");
    }
    @RequestMapping("/toextraction")
    private ModelAndView toextraction(HttpServletRequest request, HttpServletResponse response,
                                       String targetUrl) throws IOException {
        return new ModelAndView("extraction/extraction");
    }
    @RequestMapping("/correlation")
    @ResponseBody
    public StringMap correlation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Make sure Neo4j Driver is registered
        Class.forName("org.neo4j.jdbc.Driver");
        final Properties props = new Properties();
        props.put( Driver.USER, "neo4j" );
        props.put( Driver.PASSWORD, "neo4jliu" );
        props.setProperty( Driver.LEGACY, "true" );
        // Connect
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/",props);
        StringMap map=new StringMap();
        List<String> nodelist=new ArrayList<String>();
        // Querying
        try{
            Statement stmt = con.createStatement();
//          解析出所有节点
            ResultSet rs1 = stmt.executeQuery("MATCH (p:person_l)  RETURN p.name,p.classnum,p.weight");
            List<Node_name> nodes=new ArrayList<Node_name>();
            while(rs1.next())
            {
                Node_name node_name = new Node_name();
                node_name.setName(rs1.getString("p.name"));
                node_name.setClassnum(rs1.getString("p.classnum"));
                node_name.setWeight(rs1.getFloat("p.weight"));
                int flag=0;
                for (String node : nodelist) {
                    if (node.equals(node_name.getName())) {
                        flag = 1;
                    }
                }
                if(flag==0){
                    nodes.add(node_name);
                    nodelist.add(node_name.getName());
                }
                System.out.println("node："+rs1.getString("p.classnum"));
            }
//          解析节点之间的关系
            ResultSet rs2 = stmt.executeQuery("MATCH (p1)-[r:know]->(p2) where r.count>20 RETURN p1.name,r.count,p2.name");
            List<Node_link> links=new ArrayList<Node_link>();
            while(rs2.next())
            {
                Node_link node_link= new Node_link();
                node_link.setSourcenode(rs2.getString("p1.name"));
                node_link.setTargetnode(rs2.getString("p2.name"));
                node_link.setValue(rs2.getFloat("r.count"));
                links.add(node_link);
//                System.out.println(rs.getString("p1.name")+"->"+rs.getString("r.count")+"->"+rs.getString("p2.name"));
                System.out.println("sourcenode:"+node_link.getSourcenode()+"\n"+"targetnode:"+node_link.getTargetnode()+"\n"+"value2:"+node_link.getValue());
            }
            map.set("nodes",nodes);
            map.set("links",links);
        }
        catch(Exception ignored){

        }
        return  map;
    }


    @RequestMapping("/extraction")
    @ResponseBody
    public StringMap extraction(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Class.forName("org.neo4j.jdbc.Driver");
        final Properties props = new Properties();
        props.put( Driver.USER, "neo4j" );
        props.put( Driver.PASSWORD, "neo4jliu" );
        props.setProperty( Driver.LEGACY, "true" );
        // Connect
        Connection con = DriverManager.getConnection("jdbc:neo4j://localhost:7474/",props);
        StringMap map=new StringMap();
        List<String> nodelist=new ArrayList<String>();
        //用于存储节点，防止重复节点的产生
        try{
            Statement stmt = con.createStatement();
//          解析出所有节点
            ResultSet rs1 = stmt.executeQuery("MATCH (p:Leader_l) RETURN p.name,p.weight");
            List<Node_leader> nodes=new ArrayList<Node_leader>();
            while(rs1.next())
            {
                Node_leader node_leader = new Node_leader();
                node_leader.setName(rs1.getString("p.name"));
                node_leader.setWeight(rs1.getFloat("p.weight"));
                int flag=0;
                for (String node : nodelist) {
                    if (node.equals(node_leader.getName())) {
                        flag = 1;
                    }
                }
                if(flag==0){
                    nodes.add(node_leader);
                    nodelist.add(node_leader.getName());
                }
                System.out.println("node："+rs1.getString("p.name"));
            }
            //          解析节点之间的关系
            ResultSet rs2 = stmt.executeQuery("MATCH (p1)-[r:duty]->(p2) RETURN p1.name,r.duty,p2.name");
            List<Node_relation> links_duty=new ArrayList<Node_relation>();
            while(rs2.next())
            {
                Node_relation node_relation= new Node_relation();
                node_relation.setSourcenode(rs2.getString("p1.name"));
                node_relation.setTargetnode(rs2.getString("p2.name"));
                node_relation.setRelation(rs2.getString("r.duty"));
                links_duty.add(node_relation);
//                System.out.println(rs.getString("p1.name")+"->"+rs.getString("r.count")+"->"+rs.getString("p2.name"));
                System.out.println("sourcenode:"+node_relation.getSourcenode()+"\n"+"targetnode:"+node_relation.getTargetnode()+"\n"+"value:"+node_relation.getRelation());
            }
            ResultSet rs3 = stmt.executeQuery("MATCH (p1)-[r:execute]->(p2) RETURN p1.name,r.execute,p2.name");
            List<Node_relation> links_execute=new ArrayList<Node_relation>();
            while(rs3.next())
            {
                Node_relation node_relation2= new Node_relation();
                node_relation2.setSourcenode(rs3.getString("p1.name"));
                node_relation2.setTargetnode(rs3.getString("p2.name"));
                node_relation2.setRelation(rs3.getString("r.execute"));
                links_execute.add(node_relation2);
//                System.out.println(rs.getString("p1.name")+"->"+rs.getString("r.count")+"->"+rs.getString("p2.name"));
                System.out.println("sourcenode2:"+node_relation2.getSourcenode()+"\n"+"targetnode2:"+node_relation2.getTargetnode()+"\n"+"value:"+node_relation2.getRelation());
            }
            map.set("nodes",nodes);
            map.set("links_duty",links_duty);
            map.set("links_execute",links_execute);
        }
        catch(Exception ignored){

        }
        return map;
    }
}