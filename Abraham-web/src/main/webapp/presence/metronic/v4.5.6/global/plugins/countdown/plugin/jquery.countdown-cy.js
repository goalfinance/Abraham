﻿/* http://keith-wood.name/countdown.html Welsh initialisation for the jQuery countdown extension Written by Gareth Jones | http://garethvjones.com | October 2011. */(function ($) {    $.countdown.regional['cy'] = {        labels: ['Blynyddoedd', 'Mis', 'Wythnosau', 'Diwrnodau', 'Oriau', 'Munudau', 'Eiliadau'],        labels1: ['Blwyddyn', 'Mis', 'Wythnos', 'Diwrnod', 'Awr', 'Munud', 'Eiliad'],        compactLabels: ['b', 'm', 'w', 'd'],        whichLabels: null,        digits: ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'],        timeSeparator: ':', isRTL: false    };    $.countdown.setDefaults($.countdown.regional['cy']);})(jQuery);