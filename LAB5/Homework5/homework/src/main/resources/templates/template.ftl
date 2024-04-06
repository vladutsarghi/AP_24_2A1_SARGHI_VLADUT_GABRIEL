<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <title>Directory Report</title>
        <style>
                body { font-family: Arial, sans-serif; }
                .directory { margin-left: 20px; }
                .file { margin-left: 40px; color: grey; }
        </style>
</head>
<body>
<h1>Directory Report</h1>
<div class="directory">
        <#-- This is the root directory -->
        <strong>Directory:</strong> ${rootDirectory.name}
        <#-- Recursive macro to display directories and files -->
        <@displayDirectory directory=rootDirectory/>
</div>

<#macro displayDirectory directory>
        <#if directory.directories?has_content>
                <#list directory.directories as subDirectory>
                        <div class="directory">
                                <strong>Subdirectory:</strong> ${subDirectory.name}
                                <@displayDirectory directory=subDirectory/>
                        </div>
                </#list>
        </#if>
        <#if directory.files?has_content>
                <#list directory.files as file>
                        <div class="file">File: ${file.name}</div>
                </#list>
        </#if>
</#macro>
</body>
</html>
