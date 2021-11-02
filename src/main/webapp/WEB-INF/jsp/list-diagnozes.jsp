<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>List of Diagnozes:</H1>
<!--
<p>${diagnozes}</p>
-->
<table border="1">
<caption>Diagnozes</caption>
<thead>
<tr>
<th>Paciento Id</th>
<th>Gydytojas</th>
<th>Diagnoze</th>
<th>Data</th>
<th>Update</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${diagnozes}" var="diagnozes">
<tr>
<td>${diagnozes.pacientoId}</td>
<td>${gydytojai.get(diagnozes.getId()).getVardas()}</td>
<td>${diagnozes.diagnoze}</td>
<td>${diagnozes.data}</td>
<td><a type="button" href="/update-diagnozes/${diagnozes.id}">UPDATE</a></td>
<td><a type="button" href="/delete-diagnozes/${diagnozes.id}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>
<div>
<a href="add-diagnozes">ADD Diagnozes</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>