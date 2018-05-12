package generator

data class CodeEntity(
        var name: String? = null,
        var fields: List<CodeField>
)