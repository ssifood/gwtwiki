<%--

  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, version 2.1, dated February 1999.

  This program is free software; you can redistribute it and/or modify
  it under the terms of the latest version of the GNU Lesser General
  Public License as published by the Free Software Foundation;

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this program (LICENSE.txt); if not, write to the Free Software
  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

--%>
<div id="tab-menu">
<c:forEach items="${pageInfo.tabMenu}" var="menuItem" varStatus="status">
	<c:set var="menuText" value="${menuItem.value}" />
	<%-- FIXME - the print target check is an ugly hack.  need to find a better way. --%>
	<c:if test="${menuText.key == 'tab.common.print'}"><div class="tab-item"><jamwiki:link value="${menuItem.key}" target="${pageInfo.printTarget}"><fmt:message key="${menuText.key}"><fmt:param value="${menuText.params[0]}" /></fmt:message></jamwiki:link></div></c:if>
	<c:if test="${menuText.key != 'tab.common.print'}"><div class="tab-item"><jamwiki:link value="${menuItem.key}"><fmt:message key="${menuText.key}"><fmt:param value="${menuText.params[0]}" /></fmt:message></jamwiki:link></div></c:if>
</c:forEach>
</div>
<div class="clear"></div>
