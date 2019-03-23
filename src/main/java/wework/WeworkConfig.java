package wework;

public class WeworkConfig {
    public String agentId="";
    public String secret="LdvHPA3MQzeFWR5j3Mex51f_6lDmAXMj-4f61qj4Nv0";
    public String corpid="wwc8e86fdab3ffa2fb";
    public String contactSecret= "jkZF-8ANg1LrRcmADk8-gLZdYExV7Nz_et0FlKB4U2A";

    private static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if(weworkConfig==null){
            weworkConfig=new WeworkConfig();
        }
        return weworkConfig;
    }


}