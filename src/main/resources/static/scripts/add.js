$(function () {
    var currentDate = new Date();
    $("#datepicker").datepicker({
        defaultDate: currentDate,
        minDate: currentDate,
        maxDate: "+1m",
        currentText: "Today",
        dateFormat: 'mm/dd/yy'
    });
    $("#datepicker").change(function() {
        var selectedDate = $("#datepicker").val();
        $.ajax({url: "/availability/hours", data: {date: selectedDate}, success: function(response) {
            var beginTime = response.beginHour > 12 ?
                    (response.beginHour - 12) + ":00 PM" :
                    response.beginHour + ":00 AM";
            var endTime = response.endHour > 12 ?
                    (response.endHour - 12) + ":00 PM" :
                    response.endHour + ":00 AM";
            $("#timespinner").timespinner({
                min: beginTime,
                max: endTime
            });
            var currentTimeSel = $("#timespinner").val();
            if (currentTimeSel != null) {
                currentTimeSel = Date.parse("January 1, 2000, " + currentTimeSel);
                var beginTimeDate = Date.parse("January 1, 2000, " + beginTime);
                var endTimeDate = Date.parse("January 1, 2000, " + endTime);
                if (currentTimeSel < beginTimeDate) {
                    $("#timespinner").val(beginTime);
                } else if (currentTimeSel >= endTimeDate) {
                    $("#timespinner").val(endTime);
                }
            } else {
                $("#timespinner").val(beginTime);
            }
        }});
    });
    $("#datepicker").val($.datepicker.formatDate('mm/dd/yy', currentDate));
    $.widget("ui.timespinner", $.ui.spinner, {
        options: {
            // seconds
            step: 60 * 1000 * 15,
            // hours
            page: 60
        },
   
        _parse: function( value ) {
            if ( typeof value === "string" ) {
                // already a timestamp
                if ( Number( value ) == value ) {
                    return Number( value );
                }
                return +Globalize.parseDate( value );
            }
            return value;
        },

        _format: function( value ) {
            return Globalize.format( new Date(value), "t" );
        }
    });

    $("#timespinner").timespinner();
    $("#timespinner").prop("readonly", true);
    $("#datepicker").change();
});