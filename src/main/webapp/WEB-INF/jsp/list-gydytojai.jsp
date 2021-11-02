<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>List of Gydytojai:</H1>

<!--
<p>${gydytojai}</p>
-->

<table border="1">
<thead>
<tr>
<th>Id</th><th>Vardas</th><th>Telefono Numeris</th><th>Update</th><th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${gydytojai}" var="gydytojas">
<tr>
<td>${gydytojas.id}</td>
<td>${gydytojas.vardas}</td>
<td>${gydytojas.telNr}</td>
<td><a type="button" href="/update-gydytojas/${gydytojas.id}">UPDATE</a></td>
<td><a type="button" href="/delete-gydytojas/${gydytojas.id}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>

<div>
<a href="add-gydytojas">ADD Gydytojas</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>