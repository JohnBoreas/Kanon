/**
 * 0-否;1-是
 * @param rowValue
 * @returns {string}
 */
function system_base_formatter(rowValue) {
    if (rowValue == '1') {
        return '<span class="label label-success">是</span>';
    }
    else if (rowValue == '0') {
        return '<span class="label label-error">否</span>';
    }
}