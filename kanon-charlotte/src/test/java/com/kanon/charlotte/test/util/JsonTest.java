package com.kanon.charlotte.test.util;

import com.alibaba.fastjson.JSONObject;
import com.kanon.charlotte.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/6/9 18:23
 */
public class JsonTest {

    public static ObjectMapper objectMapper;

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.exchange("https://datacenter-web.eastmoney.com/api/data/v1/get?reportName=RPT_MAIN_ORGHOLD&columns=ALL&quoteColumns=&filter=(SECURITY_CODE=\"603259\")(REPORT_DATE='2021-06-30')&pageNumber=1&pageSize=8&sortTypes=&sortColumns=&source=WEB&client=WEB&callback=jQuery112309570032964586863_1640331594395&_=1640331594395", HttpMethod.GET, new HttpEntity<>(null, null), String.class).getBody());
        String content = "{\"code\":200,\"msg\":\"SUCCESS\",\"category\":[{\"name\":\"\\u5c45\\u5bb6\",\"cat_id\":10},{\"name\":\"\\u7f8e\\u98df\",\"cat_id\":11},{\"name\":\"\\u513f\\u7ae5\",\"cat_id\":8},{\"name\":\"\\u5973\\u88c5\",\"cat_id\":1},{\"name\":\"\\u7f8e\\u5986\",\"cat_id\":4},{\"name\":\"\\u7537\\u88c5\",\"cat_id\":2},{\"name\":\"\\u978b\\u54c1\",\"cat_id\":6},{\"name\":\"\\u6587\\u4f53\",\"cat_id\":16},{\"name\":\"\\u5185\\u8863\",\"cat_id\":3},{\"name\":\"\\u914d\\u9970\",\"cat_id\":5},{\"name\":\"\\u6570\\u7801\",\"cat_id\":12},{\"name\":\"\\u5bb6\\u7535\",\"cat_id\":13},{\"name\":\"\\u7bb1\\u5305\",\"cat_id\":7},{\"name\":\"\\u6bcd\\u5a74\",\"cat_id\":9},{\"name\":\"\\u8f66\\u54c1\",\"cat_id\":15},{\"name\":\"\\u5ba0\\u7269\",\"cat_id\":17},{\"name\":\"\\u5176\\u4ed6\",\"cat_id\":14}],\"background\":{\"title\":\"\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FkMFAHGc22iIvp9veWdsjMy_3Xrr\",\"link\":\"https:\\/\\/www.haodanku.com\\/activity\\/618_gather\",\"statistic_tag\":\"background|8\"},\"logo\":{\"title\":\"\\u597d\\u5355\\u5e93\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/Fua0cM2J-DgmFkBA1eT5LQGabX_b\",\"link\":\"https:\\/\\/www.haodanku.com\",\"statistic_tag\":\"logo\"},\"notice\":[{\"notice_title\":\"\\u3010\\u591a\\u5154\\u6dd8\\u5ba2\\u63d2\\u4ef6\\u3011\\u91cd\\u78c5\\u6765\\u88ad\\uff0c\\u5b9a\\u5411\\u9ad8\\u4f63\\u3001\\u9690\\u85cf\\u5238\\u3001\\u5386\\u53f2\\u63a8\\u5e7f\\u65b9\\u6848\\u3001\\u5546\\u5bb6\\u8054\\u7cfb\\u65b9\\u5f0f\\u5373\\u523b\\u62e5\\u6709\\uff01\",\"add_time\":\"1622682940\",\"notice_url\":\"https:\\/\\/www.haodanku.com\\/index\\/notice_detail\\/id\\/160\"},{\"notice_title\":\"\\u597d\\u5355\\u5e93618\\u73a9\\u6cd5\\u653b\\u7565\\uff0c\\u795d\\u60a8618\\u5927\\u5356\\uff01GO!\",\"add_time\":\"1622001730\",\"notice_url\":\"https:\\/\\/www.haodanku.com\\/index\\/notice_detail\\/id\\/159\"},{\"notice_title\":\"\\u591a\\u597d\\u5355\\u62cd\\u4e86\\u62cd\\u60a8\\uff0c\\u8bf7\\u4f60\\u67e5\\u6536618\\u798f\\u5229\\u6e05\\u5355\\u653b\\u7565\",\"add_time\":\"1621649084\",\"notice_url\":\"https:\\/\\/www.haodanku.com\\/index\\/notice_detail\\/id\\/158\"},{\"notice_title\":\"\\u5982\\u4f55\\u5728618\\u5927\\u4fc3\\u62a2\\u5360\\u5148\\u673a\\uff1f618\\u798f\\u5229\\u6e05\\u5355\\u653b\\u7565\\u6765\\u5566\\uff01\",\"add_time\":\"1621590024\",\"notice_url\":\"https:\\/\\/www.haodanku.com\\/index\\/notice_detail\\/id\\/157\"},{\"notice_title\":\"\\u91cd\\u8981\\u901a\\u77e5\\uff01\\u597d\\u5355\\u5e93 x 618\\u793e\\u4ea4\\u7ec4\\u961f\\u8d5b\\u9a6c \\u74dc\\u5206\\u5343\\u4e07\\u58d5\\u793c\",\"add_time\":\"1621585867\",\"notice_url\":\"https:\\/\\/www.haodanku.com\\/index\\/notice_detail\\/id\\/156\"}],\"help\":[{\"id\":\"181\",\"title\":\"\\u54c1\\u724c\\u5e93-\\u54c1\\u724c\\u6536\\u5f55\\u6807\\u51c6\",\"addtime\":\"1617072392\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=181\",\"add_time\":\"1617072392\"},{\"id\":\"180\",\"title\":\"\\u793e\\u7fa4\\u7d20\\u6750\\u63d0\\u4ea4\\u89c4\\u8303\",\"addtime\":\"1617072359\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=180\",\"add_time\":\"1617072359\"},{\"id\":\"179\",\"title\":\"\\u653e\\u5355\\u5e38\\u89c1\\u95ee\\u9898\",\"addtime\":\"1617072329\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=179\",\"add_time\":\"1617072329\"},{\"id\":\"178\",\"title\":\"\\u5173\\u4e8e\\u5ba1\\u6838\",\"addtime\":\"1617072310\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=178\",\"add_time\":\"1617072310\"},{\"id\":\"177\",\"title\":\"\\u66f4\\u9ad8\\u989d\\u5ea6\\u4f18\\u60e0\\u5238\",\"addtime\":\"1617072294\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=177\",\"add_time\":\"1617072294\"},{\"id\":\"176\",\"title\":\"\\u53d1\\u5e03\\u5546\\u54c1\\u6ce8\\u610f\\u4e8b\\u9879\",\"addtime\":\"1617072276\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=176\",\"add_time\":\"1617072276\"},{\"id\":\"175\",\"title\":\"\\u53d1\\u5e03\\u5546\\u54c1\\u89c4\\u8303\",\"addtime\":\"1617072257\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=175\",\"add_time\":\"1617072257\"},{\"id\":\"174\",\"title\":\"\\u653e\\u5355\\u6b65\\u9aa4\\u6d41\\u7a0b\",\"addtime\":\"1617071753\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=174\",\"add_time\":\"1617071753\"},{\"id\":\"173\",\"title\":\"\\u5546\\u54c1\\u53d1\\u5e03\\u89c4\\u5219\",\"addtime\":\"1617071699\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=173\",\"add_time\":\"1617071699\"},{\"id\":\"172\",\"title\":\"\\u5173\\u4e8e\\u653e\\u5355\",\"addtime\":\"1617071663\",\"help_url\":\"\\/Help\\/new_index.html?type=2&&anchor=172\",\"add_time\":\"1617071663\"}],\"operation\":{\"operation_time\":\" 1765 \",\"seller_num\":\"3000+\",\"shop_num\":\"100\\u4e07+\",\"api_num\":\"2\\u4e07+\",\"today_seller_num\":\"20\\u4e07+\",\"today_items_num\":\"7232\",\"online_items_num\":\"7\\u4e07+\",\"today_sale_num\":\"270\\u4e07\",\"today_tkmoney_num\":\"1939\\u4e07\",\"today_coupon_num\":\"4519\\u4e07\"},\"app_recommend\":[{\"name\":\"\\u597d\\u5355\\u5e93APP\",\"image\":\"https:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FtTFMXVSg_4R3fDxGk0HA9sUQPAr\",\"hover_image\":\"https:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FpvGtCJ1_AleK9XQLdaewS10e7yr\",\"hover_text\":\"\\u624b\\u673a\\u626b\\u7801\\u4e0b\\u8f7d\\u597d\\u5355\\u5e93\",\"url\":\"http:\\/\\/app.haodanku.com\\/\"},{\"name\":\"\\u8ba2\\u5355\\u7edf\\u8ba1\\u52a9\\u624b\",\"image\":\"https:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/Fp8MOT_HEWRigxP9s8jgR4H8UCLN\",\"hover_image\":\"https:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FncHr5-hDP0F3sGuX6Zb9A96xWNg\",\"hover_text\":\"\\u5fae\\u4fe1\\u626b\\u7801\\u8fdb\\u5165\\u5c0f\\u7a0b\\u5e8f\",\"url\":\"https:\\/\\/www.haodanku.com\\/weapp\"},{\"name\":\"\\u63a8\\u5e7f\\u5c0f\\u5de5\\u5177\",\"image\":\"https:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FhTJWHwV2c7Cd54sTLJn6wvUI6Gm\",\"url\":\"https:\\/\\/www.haodanku.com\\/tool\\/tools\"}],\"index_block\":[{\"tag\":\"ads_1\",\"title\":\"\\u56fe\\u7247\\u5e7f\\u544a\",\"location\":\"ads_1\",\"name\":\"component-ads\"},{\"tag\":\"tongleiheji\",\"title\":\"\\u540c\\u7c7b\\u5408\\u96c6\",\"location\":\"ads_3\",\"name\":\"component-same\"},{\"tag\":\"jiujiaoshangpin\",\"title\":\"\\u805a\\u96c6\\u5546\\u54c1\",\"location\":\"ads_7\",\"name\":\"component-focus\"},{\"tag\":\"baokuankuaiqiang\",\"title\":\"\\u7206\\u6b3e\\u5feb\\u62a2\",\"location\":\"ads_9\",\"name\":\"component-fastbuy\"},{\"tag\":\"haodanpaihangbang\",\"title\":\"\\u597d\\u5355\\u6392\\u884c\\u699c\",\"location\":\"ads_11\",\"name\":\"component-topitem\"},{\"tag\":\"pinpaihui\",\"title\":\"\\u54c1\\u724c\\u6c47\",\"location\":\"ads_12\",\"name\":\"component-brand\"},{\"tag\":\"remendouhuo\",\"title\":\"\\u70ed\\u95e8\\u6296\\u8d27\",\"location\":\"ads_13\",\"name\":\"component-trill\"},{\"tag\":\"haodanyugao\",\"title\":\"\\u597d\\u5355\\u9884\\u544a\",\"location\":\"ads_14\",\"name\":\"component-herald\"}],\"left_guide\":[{\"tag\":\"tongleiheji\",\"title\":\"618\\u7cbe\\u9009\\u597d\\u8d27\",\"location\":\"ads_3\"},{\"tag\":\"jiujiaoshangpin\",\"title\":\"\\u65b0\\u54c1\\u9996\\u63a8\",\"location\":\"ads_7\"},{\"tag\":\"baokuankuaiqiang\",\"title\":\"\\u7206\\u6b3e\\u5feb\\u62a2\",\"location\":\"ads_9\"},{\"tag\":\"haodanpaihangbang\",\"title\":\"\\u597d\\u5355\\u6392\\u884c\\u699c\",\"location\":\"ads_11\"},{\"tag\":\"pinpaihui\",\"title\":\"\\u54c1\\u724c\\u6c47\",\"location\":\"ads_12\"},{\"tag\":\"remendouhuo\",\"title\":\"\\u70ed\\u95e8\\u6296\\u8d27\",\"location\":\"ads_13\"},{\"tag\":\"haodanyugao\",\"title\":\"\\u597d\\u5355\\u9884\\u544a\",\"location\":\"ads_14\"}],\"banner\":[{\"id\":\"140\",\"title\":\"618\\u7206\\u6b3e\\u4e13\\u9898\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FplnF9C95elspSpIlcA2jCAbmLMH\",\"img_thumb\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/Fp7B-wCBK02ivT1Tw5rleCqglC8i\",\"link\":\"http:\\/\\/www.haodanku.com\\/IndexActivity\\/index?activity=89\",\"link_title\":\"\",\"statistic_tag\":\"ads|140\"},{\"id\":\"141\",\"title\":\"\\u91cf\\u65e0\\u5fe7\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FmrAgwVhiLBBTHnNLrvdapkwNgLz\",\"img_thumb\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/Fmd_hzHUxLN9dyLQLwTmeukbbSM5\",\"link\":\"http:\\/\\/www.haodanku.com\\/IndexActivity\\/index?activity=90\",\"link_title\":\"\",\"statistic_tag\":\"ads|141\"},{\"id\":\"122\",\"title\":\"\\u597d\\u5355\\u5e93CMS\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FoxzFI9dMJjCUGs5YlNhtzwoom_9\",\"img_thumb\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FmRouayb93F5Sf0__RIdx3efl3VT\",\"link\":\"https:\\/\\/www.haodanku.com\\/cms\\/intro\",\"link_title\":\"\",\"statistic_tag\":\"ads|122\"},{\"id\":\"135\",\"title\":\"\\u591a\\u5154\\u6d4f\\u89c8\\u5668\\u63d2\\u4ef6\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/Fq6ypuV2ThSMr2zV1iiHkOg-Y0-s\",\"img_thumb\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/Fq2B94yCcZisKAphYTzgc3ldYJ5v\",\"link\":\"https:\\/\\/duotu.pro\\/plugs\",\"link_title\":\"\",\"statistic_tag\":\"ads|135\"},{\"id\":\"116\",\"title\":\"\\u5929\\u732b\\u8d85\\u5e02\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FlSG0EjEElFCE8hBmDSNLvnfgmaJ\",\"img_thumb\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FpZspYWjFAVxM7AkRGwN0SM_yI37\",\"link\":\"http:\\/\\/www.haodanku.com\\/IndexActivity\\/index?activity=79\",\"link_title\":\"\",\"statistic_tag\":\"ads|116\"},{\"id\":\"23\",\"title\":\"\\u5b9a\\u5411\\u798f\\u5229\\u597d\\u7269\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FqEXht49j9LHTUpBgTQDC2rKYooP\",\"img_thumb\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FuZUIQaKiT66ZKgtfxsVLuPzj3mU\",\"link\":\"http:\\/\\/www.haodanku.com\\/IndexActivity\\/index?activity=14\",\"link_title\":\"\\u5b9a\\u5411\\u670d\\u52a1\\u597d\\u7269\",\"statistic_tag\":\"ads|23\"}],\"activity_nav\":{\"id\":\"138\",\"title\":\"618\\u798f\\u5229\\u6e05\\u5355\",\"img\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/Fpt8jcJMdx2CalKQOyL_dhm7sOcZ\",\"link\":\"https:\\/\\/www.haodanku.com\\/activity\\/618_gather\",\"link_title\":\"\",\"statistic_tag\":\"ads|138\"},\"watermark\":\"\",\"top_banner\":[{\"image\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FnP0ddUXWubzkle6zrj4Pw18ev_R\",\"url\":\"https:\\/\\/www.haodanku.com\\/activity\\/618_gather\"},{\"image\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FgQmanOPfZF88aouyuCNQXB1tCdM\",\"url\":\"https:\\/\\/www.haodanku.com\\/cms\\/intro\"},{\"image\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FmEz-03IVQIsN3a3MDl8z01PzcL_\",\"url\":\"https:\\/\\/duotu.pro\\/plugs\"},{\"image\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/Fkx9ofk7gl1pHlXjb4-GT5je5Xm5\",\"url\":\"https:\\/\\/duotu.pro\"},{\"image\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FsnJkeJ9c-bMhV2Vc3tOTMse1D7c\",\"url\":\"https:\\/\\/www.haodanku.com\\/openapi\\/source_market_inner?id=21\"},{\"image\":\"http:\\/\\/img-haodanku-com.cdn.fudaiapp.com\\/FjV4ZKV6msr1pTCypXc72QCp42N0\",\"url\":\"https:\\/\\/www.duohaodan.com\\/\"}]}";
                //"{\"rc\":0,\"rt\":6,\"svr\":182997191,\"lt\":1,\"full\":1,\"data\":{\"total\":4511,\"diff\":[{\"f1\":2,\"f2\":3.16,\"f3\":10.1,\"f4\":0.29,\"f5\":1784272,\"f6\":556215824.0,\"f7\":10.45,\"f8\":3.52,\"f9\":-51.52,\"f10\":3.71,\"f11\":0.0,\"f12\":\"002506\",\"f13\":0,\"f14\":\"协鑫集成\",\"f15\":3.16,\"f16\":2.86,\"f17\":2.88,\"f18\":2.87,\"f20\":18503388428,\"f21\":16038110465,\"f22\":0.0,\"f23\":4.45,\"f24\":-8.67,\"f25\":-25.65,\"f62\":191996994.0,\"f115\":-7.15,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":2.62,\"f3\":10.08,\"f4\":0.24,\"f5\":1434259,\"f6\":360366016.0,\"f7\":10.5,\"f8\":6.35,\"f9\":1863.14,\"f10\":1.06,\"f11\":0.0,\"f12\":\"600759\",\"f13\":1,\"f14\":\"洲际油气\",\"f15\":2.62,\"f16\":2.37,\"f17\":2.42,\"f18\":2.38,\"f20\":5930389697,\"f21\":5916442127,\"f22\":0.0,\"f23\":1.12,\"f24\":28.43,\"f25\":46.37,\"f62\":89739072.0,\"f115\":27.82,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":6.77,\"f3\":10.08,\"f4\":0.62,\"f5\":761686,\"f6\":498300064.0,\"f7\":11.87,\"f8\":6.06,\"f9\":6.86,\"f10\":0.86,\"f11\":0.0,\"f12\":\"000949\",\"f13\":0,\"f14\":\"新乡化纤\",\"f15\":6.77,\"f16\":6.04,\"f17\":6.23,\"f18\":6.15,\"f20\":8514331452,\"f21\":8511157175,\"f22\":0.0,\"f23\":2.03,\"f24\":61.19,\"f25\":107.03,\"f62\":92117737.0,\"f115\":24.48,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":5.57,\"f3\":10.08,\"f4\":0.51,\"f5\":449504,\"f6\":243949006.0,\"f7\":10.08,\"f8\":3.75,\"f9\":9.73,\"f10\":2.17,\"f11\":0.0,\"f12\":\"601101\",\"f13\":1,\"f14\":\"昊华能源\",\"f15\":5.57,\"f16\":5.06,\"f17\":5.16,\"f18\":5.06,\"f20\":6683990375,\"f21\":6683990375,\"f22\":0.0,\"f23\":0.85,\"f24\":38.21,\"f25\":18.26,\"f62\":37870143.0,\"f115\":34.35,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":5.03,\"f3\":10.07,\"f4\":0.46,\"f5\":5322301,\"f6\":2557328336.0,\"f7\":15.75,\"f8\":15.65,\"f9\":14.14,\"f10\":2.03,\"f11\":0.0,\"f12\":\"000683\",\"f13\":0,\"f14\":\"远兴能源\",\"f15\":5.03,\"f16\":4.31,\"f17\":4.5,\"f18\":4.57,\"f20\":18836771505,\"f21\":17109592302,\"f22\":0.0,\"f23\":1.78,\"f24\":84.25,\"f25\":132.87,\"f62\":212156320.0,\"f115\":41.76,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":10.51,\"f3\":10.05,\"f4\":0.96,\"f5\":233067,\"f6\":240928338.0,\"f7\":10.26,\"f8\":9.95,\"f9\":40.42,\"f10\":5.07,\"f11\":0.0,\"f12\":\"002553\",\"f13\":0,\"f14\":\"南方轴承\",\"f15\":10.51,\"f16\":9.53,\"f17\":9.56,\"f18\":9.55,\"f20\":3657480000,\"f21\":2462432053,\"f22\":0.0,\"f23\":3.42,\"f24\":-6.24,\"f25\":0.57,\"f62\":86394791.0,\"f115\":9.21,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":8.55,\"f3\":10.04,\"f4\":0.78,\"f5\":924922,\"f6\":770913088.0,\"f7\":10.68,\"f8\":5.53,\"f9\":7.08,\"f10\":1.8,\"f11\":0.0,\"f12\":\"601001\",\"f13\":1,\"f14\":\"晋控煤业\",\"f15\":8.55,\"f16\":7.72,\"f17\":7.87,\"f18\":7.77,\"f20\":14310135000,\"f21\":14310135000,\"f22\":0.12,\"f23\":1.81,\"f24\":64.42,\"f25\":35.71,\"f62\":77700092.0,\"f115\":13.14,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":8.99,\"f3\":10.04,\"f4\":0.82,\"f5\":791336,\"f6\":678424784.0,\"f7\":10.65,\"f8\":16.8,\"f9\":15.2,\"f10\":0.86,\"f11\":0.0,\"f12\":\"002536\",\"f13\":0,\"f14\":\"飞龙股份\",\"f15\":8.99,\"f16\":8.12,\"f17\":8.13,\"f18\":8.17,\"f20\":4501399208,\"f21\":4235394439,\"f22\":0.0,\"f23\":2.0,\"f24\":71.56,\"f25\":64.35,\"f62\":44201528.0,\"f115\":25.96,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":11.96,\"f3\":10.03,\"f4\":1.09,\"f5\":181345,\"f6\":208222482.0,\"f7\":8.56,\"f8\":7.8,\"f9\":236.69,\"f10\":5.61,\"f11\":0.0,\"f12\":\"603918\",\"f13\":1,\"f14\":\"金桥信息\",\"f15\":11.96,\"f16\":11.03,\"f17\":11.3,\"f18\":10.87,\"f20\":3375443388,\"f21\":2779990174,\"f22\":0.0,\"f23\":3.14,\"f24\":22.17,\"f25\":15.67,\"f62\":20748709.0,\"f115\":33.55,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":4.06,\"f3\":10.03,\"f4\":0.37,\"f5\":843614,\"f6\":331026720.0,\"f7\":9.21,\"f8\":8.79,\"f9\":28.25,\"f10\":1.22,\"f11\":0.0,\"f12\":\"000545\",\"f13\":0,\"f14\":\"金浦钛业\",\"f15\":4.06,\"f16\":3.72,\"f17\":3.72,\"f18\":3.69,\"f20\":4006542370,\"f21\":3895176570,\"f22\":0.0,\"f23\":2.17,\"f24\":30.97,\"f25\":16.67,\"f62\":81518449.0,\"f115\":-18.44,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":13.72,\"f3\":10.02,\"f4\":1.25,\"f5\":182375,\"f6\":242320351.0,\"f7\":9.14,\"f8\":17.1,\"f9\":15.65,\"f10\":1.82,\"f11\":0.0,\"f12\":\"003016\",\"f13\":0,\"f14\":\"欣贺股份\",\"f15\":13.72,\"f16\":12.58,\"f17\":12.58,\"f18\":12.47,\"f20\":5922494564,\"f21\":1463467124,\"f22\":0.0,\"f23\":1.95,\"f24\":58.98,\"f25\":46.11,\"f62\":36205322.0,\"f115\":25.38,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":5.05,\"f3\":10.02,\"f4\":0.46,\"f5\":1677388,\"f6\":827685008.0,\"f7\":10.68,\"f8\":8.71,\"f9\":18.85,\"f10\":1.97,\"f11\":0.0,\"f12\":\"600063\",\"f13\":1,\"f14\":\"皖维高新\",\"f15\":5.05,\"f16\":4.56,\"f17\":4.62,\"f18\":4.59,\"f20\":9725768195,\"f21\":9725768195,\"f22\":0.0,\"f23\":1.71,\"f24\":14.51,\"f25\":41.85,\"f62\":143738892.0,\"f115\":14.07,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":22.29,\"f3\":10.02,\"f4\":2.03,\"f5\":24016,\"f6\":52066417.0,\"f7\":11.3,\"f8\":2.89,\"f9\":37.67,\"f10\":7.59,\"f11\":0.0,\"f12\":\"002892\",\"f13\":0,\"f14\":\"科力尔\",\"f15\":22.29,\"f16\":20.0,\"f17\":20.42,\"f18\":20.26,\"f20\":3167854800,\"f21\":1849193446,\"f22\":0.0,\"f23\":4.31,\"f24\":11.51,\"f25\":-2.62,\"f62\":10502899.0,\"f115\":30.63,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":28.88,\"f3\":10.02,\"f4\":2.63,\"f5\":76055,\"f6\":209172289.0,\"f7\":13.98,\"f8\":7.08,\"f9\":86.45,\"f10\":1.95,\"f11\":0.0,\"f12\":\"002881\",\"f13\":0,\"f14\":\"美格智能\",\"f15\":28.88,\"f16\":25.21,\"f17\":26.88,\"f18\":26.25,\"f20\":5329486320,\"f21\":3104309756,\"f22\":0.0,\"f23\":9.39,\"f24\":55.02,\"f25\":45.71,\"f62\":-709280.0,\"f115\":137.97,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":25.81,\"f3\":10.02,\"f4\":2.35,\"f5\":64249,\"f6\":163382061.0,\"f7\":7.72,\"f8\":15.96,\"f9\":399.56,\"f10\":2.04,\"f11\":0.0,\"f12\":\"003032\",\"f13\":0,\"f14\":\"传智教育\",\"f15\":25.81,\"f16\":24.0,\"f17\":24.4,\"f18\":23.46,\"f20\":10387169975,\"f21\":1038716998,\"f22\":0.0,\"f23\":9.03,\"f24\":15.38,\"f25\":205.81,\"f62\":10350986.0,\"f115\":140.53,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":6.81,\"f3\":10.02,\"f4\":0.62,\"f5\":429115,\"f6\":285787392.0,\"f7\":12.12,\"f8\":11.71,\"f9\":5.32,\"f10\":1.7,\"f11\":0.0,\"f12\":\"600889\",\"f13\":1,\"f14\":\"南京化纤\",\"f15\":6.81,\"f16\":6.06,\"f17\":6.19,\"f18\":6.19,\"f20\":2494816328,\"f21\":2494816328,\"f22\":0.0,\"f23\":1.64,\"f24\":49.02,\"f25\":56.19,\"f62\":67452080.0,\"f115\":15.16,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":6.92,\"f3\":10.02,\"f4\":0.63,\"f5\":470421,\"f6\":302048288.0,\"f7\":14.63,\"f8\":11.07,\"f9\":70.95,\"f10\":1.47,\"f11\":0.0,\"f12\":\"600630\",\"f13\":1,\"f14\":\"龙头股份\",\"f15\":6.92,\"f16\":6.0,\"f17\":6.37,\"f18\":6.29,\"f20\":2940042251,\"f21\":2940042251,\"f22\":0.0,\"f23\":1.91,\"f24\":37.85,\"f25\":18.09,\"f62\":45765285.0,\"f115\":-15.91,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":19.56,\"f3\":10.01,\"f4\":1.78,\"f5\":1874,\"f6\":3665270.0,\"f7\":0.0,\"f8\":0.37,\"f9\":33.89,\"f10\":0.39,\"f11\":0.0,\"f12\":\"605319\",\"f13\":1,\"f14\":\"无锡振华\",\"f15\":19.56,\"f16\":19.56,\"f17\":19.56,\"f18\":17.78,\"f20\":3912000000,\"f21\":978000000,\"f22\":0.0,\"f23\":2.3,\"f24\":74.33,\"f25\":74.33,\"f62\":2793696.0,\"f115\":30.62,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":37.26,\"f3\":10.01,\"f4\":3.39,\"f5\":503527,\"f6\":1759910432.0,\"f7\":16.45,\"f8\":28.38,\"f9\":-384.08,\"f10\":1.11,\"f11\":0.0,\"f12\":\"000995\",\"f13\":0,\"f14\":\"皇台酒业\",\"f15\":37.26,\"f16\":31.69,\"f17\":32.3,\"f18\":33.87,\"f20\":6610222080,\"f21\":6610222080,\"f22\":0.0,\"f23\":51.05,\"f24\":96.83,\"f25\":16.8,\"f62\":-19891903.0,\"f115\":185.7,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":56.94,\"f3\":10.01,\"f4\":5.18,\"f5\":46801,\"f6\":257307702.0,\"f7\":9.23,\"f8\":4.93,\"f9\":15.96,\"f10\":1.92,\"f11\":0.0,\"f12\":\"002978\",\"f13\":0,\"f14\":\"安宁股份\",\"f15\":56.94,\"f16\":52.16,\"f17\":52.16,\"f18\":51.76,\"f20\":22832940000,\"f21\":5409300000,\"f22\":0.0,\"f23\":5.18,\"f24\":23.54,\"f25\":24.19,\"f62\":46996246.0,\"f115\":26.34,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2}]}}";
        String name = "app_recommend";
        List<?> cont = (List<?>) JsonUtils.getObject(name, content);
        for (Object o : cont) {
            System.out.println(JsonUtils.getObject("hover_image", JSONObject.toJSONString(o)));
        }
    }

    /**
     * Json字符串中取出指定节点值
     *
     * @param jsonStr json
     * @param key     key
     * @return
     */
    public static Object getValueByKey(String jsonStr, String key) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonStr).get(key);
            if (jsonNode == null) {
                return null;
            }
            if (jsonNode.isTextual()) {
                return jsonNode.asText();
            } else if (jsonNode.isArray()) {
                return jsonNode.findValuesAsText(key);
            } else {
                return jsonNode.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param jsonStr   json
     * @param valueType class
     * @return T
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json数组转List
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
