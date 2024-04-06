<!DOCTYPE html>
<html>
<head>
    <title>Folder Structure Report</title>
    <style>
        body { font-family: Arial, sans-serif; }
        ul { list-style-type: none; }
        li { margin: 5px; }
        .folder { font-weight: bold; }
    </style>
</head>
<body>
    <h1>Folder Structure Report</h1>
    <#-- Recursive list rendering based on passed model -->
    <#macro renderList items>
        <ul>
        <#list items as item>
            <li class="${item.type}">
                <#if item.type == "folder">
                    ${item.name}
                    <@renderList items=item.children/>
                <#else>
                    ${item.name}
                </#if>
            </li>
        </#list>
        </ul>
    </#macro>

    <@renderList items=folders/>
</body>
</html>
