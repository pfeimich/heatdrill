package ch.so.agi.heatdrill;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

@RestController
public class Controller {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/service")
    public DrillInformation getDrillInformation(
    			@RequestParam(value="x") int x,
    			@RequestParam(value="y") int y) {
    	
    	assertEpsg2056CoordsInKtsoBounds(x,y);
    	
    	DrillInformation drillInfo = queryDrillInformation(x,y);
    	
    	return drillInfo;
    }
    
    private void assertEpsg2056CoordsInKtsoBounds(int x, int y) {
    	if (x < 2_591_065 || x > 2_646_363)
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
    				"X-Coordinate not between 2'591'065 and 2'646'363 (EPSG:2056)");
    	
    	if (y < 1_212_797 || y > 1_262_750)
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
    				"Y-Coordinate not between 1'212'797 and 1'262'750 (EPSG:2056)");
    }
    
    private DrillInformation queryDrillInformation(int x, int y) {
    	    	  	
    	String query = "SELECT \n" + 
	    			"	durchfuehrung_id AS requestId,\n" + 
	    			"	resultat_gesamt AS permitted,\n" + 
	    			"	tiefe AS depth,\n" + 
	    			"	resultat_gwszone AS gwsZone,\n" + 
	    			"	resultat_gw_vorkommen AS gwPresent,\n" + 
	    			"	resultat_quelle AS spring,\n" + 
	    			"	resultat_gwraum AS gwRoom,\n" + 
	    			"	resultat_altlast AS wasteSite,\n" + 
	    			"	text AS infoText\n" + 
	    			"FROM entscheidung.entscheidung_detail(?,?);";    	
    	
    	RowMapper<DrillInformation> mapper = new BeanPropertyRowMapper<DrillInformation>(DrillInformation.class);    	
    	Integer[] params = new Integer[] {x, y};
    	
    	List<DrillInformation> resultList = jdbcTemplate.query(query, params, mapper);
    	
    	assertResultIsSingleRow(resultList);
    	
    	return resultList.get(0);
    }
    
    private void assertResultIsSingleRow(List<DrillInformation> resultList) {
    	if(resultList == null || resultList.size() != 1) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
    				"Query result did not map to exactly one object of type DrillInformation");
    	}
    }
}