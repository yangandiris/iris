/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.24
 * Generated at: 2015-12-14 15:12:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class mainUI_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/struts2-core-2.3.20.jar", Long.valueOf(1420043878000L));
    _jspx_dependants.put("jar:file:/E:/JAVA_MAR/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CloudDisk/WEB-INF/lib/struts2-core-2.3.20.jar!/META-INF/struts-tags.tld", Long.valueOf(1416575674000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("\r\n");
      out.write("<title>云主页</title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-3.3.0/dist/css/bootstrap.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"jquery-ui/themes/excite-bike/jquery-ui.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mainUI.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"DataTable/css/jquery.dataTables_themeroller.css\" /> \r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"WU/webuploader.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/image.css\" />\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-2.1.3.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"jquery-ui/jquery-ui.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery.form.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"bootstrap-3.3.0/dist/js/bootstrap.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/fileselect.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/mainUI.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"DataTable/js/jquery.dataTables.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/fileupload.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"WU/webuploader.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"layer/layer.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"layer/extend/layer.ext.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/image.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body >\r\n");
      out.write("\t<!--头部开始  -->\r\n");
      out.write("\t<div class=\"top\">\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t<span class=\"glyphicon glyphicon-leaf\" style=\"margin-left:80px;\"></span>\t\r\n");
      out.write("\t\t\t<span>CloudDisk</span>\t\r\n");
      out.write("\t\t\t<script language=\"javascript\">     \r\n");
      out.write("\t\t\t   var date = new Date(");
      out.print(new java.util.Date().getTime());
      out.write(");   \r\n");
      out.write("\t\t\t   function run() {       \r\n");
      out.write("\t\t\t   date.setSeconds(date.getSeconds() + 1);          \r\n");
      out.write("\t\t\t   document.getElementById(\"currentTime\").innerHTML = date.toLocaleString();      }     \r\n");
      out.write("\t\t\t   window.setInterval(\"run();\", 1000); \r\n");
      out.write("\t\t\t </script>\r\n");
      out.write("            <time id=\"currentTime\"></time> \t\t\r\n");
      out.write("\t\t\t<div id=\"usermanager\">\r\n");
      out.write("\t\t\t\t<img class=\"img-circle\" src=\"img/user.png\" />\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<ol class=\"user-menu\" hidden>\r\n");
      out.write("\t\t\t<li id=\"nickname\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${username }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</li>\r\n");
      out.write("\t\t\t<li><a href=\"javascript:void(0)\"><span class=\"glyphicon glyphicon-user\"\r\n");
      out.write("\t\t\t\t\tstyle=\"color: rgb(255, 140, 60);\"> </span>个人中心</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"login.html\"><span class=\"glyphicon glyphicon-refresh\"\r\n");
      out.write("\t\t\t\t\tstyle=\"color: rgb(255, 140, 60);\"></span>切换账号</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"login.html\"><span class=\"glyphicon glyphicon-off\"\r\n");
      out.write("\t\t\t\t\tstyle=\"color: rgb(255, 140, 60);\"> </span>退出</a></li>\r\n");
      out.write("\t\t</ol>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--头部结束  -->\r\n");
      out.write("\r\n");
      out.write("    <div>\r\n");
      out.write("\t<!--网盘主界面开始  -->\r\n");
      out.write("\t<div class=\"networkdisk\"  >\r\n");
      out.write("\t\t<!--网盘左边栏开始 -->\r\n");
      out.write("\t\t<div class=\"networkdisk-left\">\r\n");
      out.write("\t\t\t<div class=\"btn-group-vertical\">\r\n");
      out.write("\t\t\t\t<a  href=\"javascript:void(0)\" type=\"button\" style=\"width: 200px;\" \r\n");
      out.write("\t\t\t\t\tonclick=\"listallfile(this)\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn btn-info btn-lg networkdisk-left-btn \">\r\n");
      out.write("\t\t\t\t\t<span class=\"glyphicon glyphicon-folder-close\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"color: #3C94D6;\"> </span> \r\n");
      out.write("\t\t\t\t\t所有文件\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" type=\"button\" onclick=\"listpartfile(this,'video','视频')\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn btn-info btn-lg networkdisk-left-btn\">\r\n");
      out.write("\t\t\t\t\t<span class=\"glyphicon glyphicon-facetime-video\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"color: #3C94D6;\"> </span> 视频\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" type=\"button\" onclick=\"listpartfile(this,'text','文档')\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn btn-info btn-lg networkdisk-left-btn\">\r\n");
      out.write("\t\t\t\t\t<span class=\"glyphicon glyphicon-file\" style=\"color: #3C94D6;\">\r\n");
      out.write("\t\t\t\t\t</span> 文档\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" type=\"button\" onclick=\"listpartfile(this,'image','图片')\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn btn-info btn-lg networkdisk-left-btn\">\r\n");
      out.write("\t\t\t\t\t<span class=\"glyphicon glyphicon-picture\" style=\"color: #3C94D6;\">\r\n");
      out.write("\t\t\t\t\t</span> 图片\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" type=\"button\" onclick=\"listpartfile(this,'lock','加密')\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn btn-info btn-lg networkdisk-left-btn\">\r\n");
      out.write("\t\t\t\t\t<span class=\"glyphicon glyphicon-lock\" style=\"color: #3C94D6;\">\r\n");
      out.write("\t\t\t\t\t</span> 加密文件\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!--网盘左边栏结束  -->\r\n");
      out.write("\r\n");
      out.write("\t\t<!--网盘中间栏开始  -->\r\n");
      out.write("\t\t<div class=\"networkdisk-center\">\r\n");
      out.write("\t\t\t<div class=\"networkdisk-center-top\">\r\n");
      out.write("\t\t\t\t <form class=\"networkdisk-search pull-left\" role=\"form\">\r\n");
      out.write("\t\t\t\t\t<div class=\"input-group input-group-sm\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"input-group-btn\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-info \" type=\"button\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"glyphicon glyphicon-search\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstyle=\"color: #3C94D6; font-size: 12px;\"> </span>搜索\r\n");
      out.write("\t\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t            <input id=\"filesearch\" type=\"text\" class=\"form-control \" placeholder=\"请输入你要搜索的文件\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form> \r\n");
      out.write("\t\t\t   <div class=\"networkdisk-fileopration\" hidden>\r\n");
      out.write("\t\t\t        <li><a class=\"btn btn-default\"><span class=\"glyphicon glyphicon-download\"\r\n");
      out.write("\t\t\t\t\tstyle=\"color:#9094CF;\" > </span>下   载</a></li>\r\n");
      out.write("\t\t\t        <li><a class=\"btn btn-default\" ><span class=\"glyphicon glyphicon-retweet\"\r\n");
      out.write("\t\t\t\t\tstyle=\"color: #9094CF;\"></span>  移   动</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a class=\"btn btn-default\" ><span class=\"glyphicon glyphicon-remove\"\r\n");
      out.write("\t\t\t\t\tstyle=\"color:#9094CF;\"> </span>  删   除</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"\r\n");
      out.write("\t\t\t\t\tstyle=\"color:#9094CF;\"> </span>  重命名</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a class=\"btn btn-default\"><span class=\"glyphicon glyphicon-lock\"\r\n");
      out.write("\t\t\t\t\tstyle=\"color:#9094CF;\"> </span>  保险箱</a></li>\t\r\n");
      out.write("\t\t\t   </div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div class=\"btn-group networkdisk-button\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" \r\n");
      out.write("\t\t\t\t\t\tonclick=\"fileupload(this);\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"glyphicon glyphicon-upload\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"color: rgb(44, 87, 153);\"></span> 文件上传\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t<button  type=\"button\" class=\"btn btn-default\"\r\n");
      out.write("\t\t\t\t\t    onclick=\"newfolder();\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"glyphicon glyphicon-download\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"color: rgb(44, 87, 153);\"></span> 新建文件夹\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"networkdisk-center-center \">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div tabindex=\"2\" id=\"filepages\">\r\n");
      out.write("\t\t\t\t   <div id=\"filepage-all\">\r\n");
      out.write("\t\t\t\t      <div  class=\"well well-sm \">\r\n");
      out.write("\t\t\t\t         <div class=\"filepages-all-btn pull-left\" ></div>\r\n");
      out.write("\t\t\t\t         <img class=\"filepages-change-all point img-circle\" src=\"img/change.jpg\" width=\"20px\" height=\"20px\"></img>\r\n");
      out.write("\t\t\t\t         \r\n");
      out.write("\t\t\t\t      </div>\r\n");
      out.write("\t\t\t\t      \r\n");
      out.write("\t\t\t\t      \r\n");
      out.write("\t\t\t\t      <div id=\"filepages-all-mainui\" class=\"\"></div>\r\n");
      out.write("\t\t\t\t   </div>\r\n");
      out.write("\t\t\t\t   <div id=\"filepage-text\"></div>\r\n");
      out.write("\t\t\t\t   <div id=\"filepage-photo\"></div>\r\n");
      out.write("\t\t\t\t   <div id=\"filepage-voide\"></div>\r\n");
      out.write("\t\t\t\t   <div id=\"filepage-box\"></div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!--网盘中间栏结束  -->\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--网盘主界面结束  -->\r\n");
      out.write("   \r\n");
      out.write("    </div>\r\n");
      out.write("\t<!--上传模态框开始  -->\r\n");
      out.write("\t<div id=\"uploadmodal\" hidden>\r\n");
      out.write("\t   \r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<!--上传模态框结束   -->\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("    \r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
