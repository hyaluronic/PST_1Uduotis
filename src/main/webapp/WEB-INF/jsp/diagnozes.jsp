<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Add new Diagnozes:</p>
<form:form method="post" modelAttribute="diagnozes">

	<form:input path="id" type="hidden" required="required" />
	<form:errors path="id" />

	<form:label path="pacientoId">Paciento Id</form:label>
	<form:input path="pacientoId" type="text" required="required" />
	<form:errors path="pacientoId" />

	<form:label path="gydytojoId">Gydytojo Id</form:label>
	<form:input path="gydytojoId" type="text" required="required" />
	<form:errors path="gydytojoId" />

	<form:label path="diagnoze">Diagnoze</form:label>
	<form:input path="diagnoze" type="text" required="required" />
	<form:errors path="diagnoze" />

	<form:label path="data">Data</form:label>
	<form:input path="data" type="text" required="required" />
	<form:errors path="data" />

	<button type="submit">OK</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>