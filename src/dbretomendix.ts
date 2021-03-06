import { MendixSdkClient, OnlineWorkingCopy } from 'mendixplatformsdk';
import { domainmodels } from 'mendixmodelsdk';

import populateMendixFromDBRE from './metatomendix/m2mfromdbre';

const username = 'carrascoMendix@ModelDD.org';
const apikey = '883ea2d1-12da-45d8-9474-9f7a8363771f'; // Key description "For MendixSdkTst01" created 20180506
const baseProjectName = 'ACVappMendixSdkTst01-';
const baseEntityName = 'ACVEntity_';
const client = new MendixSdkClient(username, apikey);

/* After execution, visit https://sprintr.home.mendix.com/index.html */

async function main() {
    const project = await client.platform().createNewApp(`${baseProjectName}${fDateToTimestampWOsepsStr( new Date())}`);
    const workingCopy = await project.createWorkingCopy();

    const domainModel = await loadDomainModel(workingCopy);

    try {
        populateMendixFromDBRE( domainModel, DBRE);
    }
    catch (error) {
        console.error('Error during populating Mendix model:', error);
    }

    try {
        const revision = await workingCopy.commit();
        console.log(`Successfully committed revision: ${revision.num()}. Done.`)
    }
    catch (error) {
        console.error('Error during commit Mendix model:', error);
    }
}


function loadDomainModel(workingCopy: OnlineWorkingCopy): Promise<domainmodels.DomainModel> {
    const dm = workingCopy.model().allDomainModels().filter(dm => dm.containerAsModule.name === 'MyFirstModule')[0];

    return new Promise((resolve, reject) => dm.load(resolve));
}




function fDateToDateStr( theDate : Date) : string {

    var aUTCDate = "" + theDate.getDate();
    var aUTCDateStr = "" + aUTCDate;
    if( aUTCDateStr.length < 2) {
        aUTCDateStr = "0" + aUTCDateStr;
    }

    var aUTCMonth = "" + (theDate.getMonth()+ 1);
    var aUTCMonthStr = "" + aUTCMonth;
    if( aUTCMonthStr.length < 2) {
        aUTCMonthStr = "0" + aUTCMonthStr;
    }

    var aUTCFullYear = theDate.getFullYear();
    var aUTCFullYearStr = "" + aUTCFullYear;

    var aDateStr =  aUTCDateStr + "/" + aUTCMonthStr + "/" + aUTCFullYearStr;
    return aDateStr;
}




function fDateToDateWOsepsStr( theDate : Date): string {

    var aUTCDate = "" + theDate.getDate();
    var aUTCDateStr = "" + aUTCDate;
    if( aUTCDateStr.length < 2) {
        aUTCDateStr = "0" + aUTCDateStr;
    }

    var aUTCMonth = "" + (theDate.getMonth()+ 1);
    var aUTCMonthStr = "" + aUTCMonth;
    if( aUTCMonthStr.length < 2) {
        aUTCMonthStr = "0" + aUTCMonthStr;
    }

    var aUTCFullYear = theDate.getFullYear();
    var aUTCFullYearStr = "" + aUTCFullYear;

    var aDateStr =  aUTCFullYearStr + aUTCMonthStr + aUTCDateStr;
    return aDateStr;
}




function fDateToTimestampStr( theDate : Date) {

    if( theDate == null) {
        return null;
    }

    var aDate = "" + theDate.getDate();
    var aDateStr = "" + aDate;
    if( aDateStr.length < 2) {
        aDateStr = "0" + aDateStr;
    }

    var aMonth = "" + (theDate.getMonth()+ 1);
    var aMonthStr = "" + aMonth;
    if( aMonthStr.length < 2) {
        aMonthStr = "0" + aMonthStr;
    }

    var aFullYear = theDate.getFullYear();
    var aFullYearStr = "" + aFullYear;

    var aHours = theDate.getHours();
    var aHoursStr = "" + aHours;
    if( aHoursStr.length < 2) {
        aHoursStr = "0" + aHoursStr;
    }

    var aMinutes = theDate.getMinutes();
    var aMinutesStr = "" + aMinutes;
    if( aMinutesStr.length < 2) {
        aMinutesStr = "0" + aMinutesStr;
    }

    var aSeconds = theDate.getSeconds();
    var aSecondsStr = "" + aSeconds;
    if( aSecondsStr.length < 2) {
        aSecondsStr = "0" + aSecondsStr;
    }

    var aMilliseconds = theDate.getMilliseconds();
    var aMillisecondsStr = "" + aMilliseconds;
    if( aMillisecondsStr.length < 3) {
        if( aMillisecondsStr.length < 2) {
            aMillisecondsStr = "00" + aMillisecondsStr;
        }
        else {
            aMillisecondsStr = "0" + aMillisecondsStr;
        }
    }

    var aTimestampStr = aDateStr + "/" + aMonthStr + "/" + aFullYearStr
        + " "
        + aHoursStr + ":" + aMinutesStr + ":" + aSecondsStr + "." + aMillisecondsStr;

    return aTimestampStr;
}






function fDateToTimestampWOsepsStr( theDate : Date) {

    var aDate = "" + theDate.getDate();
    var aDateStr = "" + aDate;
    if( aDateStr.length < 2) {
        aDateStr = "0" + aDateStr;
    }

    var aMonth = "" + (theDate.getMonth()+ 1);
    var aMonthStr = "" + aMonth;
    if( aMonthStr.length < 2) {
        aMonthStr = "0" + aMonthStr;
    }

    var aFullYear = theDate.getFullYear();
    var aFullYearStr = "" + aFullYear;

    var aHours = theDate.getHours();
    var aHoursStr = "" + aHours;
    if( aHoursStr.length < 2) {
        aHoursStr = "0" + aHoursStr;
    }

    var aMinutes = theDate.getMinutes();
    var aMinutesStr = "" + aMinutes;
    if( aMinutesStr.length < 2) {
        aMinutesStr = "0" + aMinutesStr;
    }

    var aSeconds = theDate.getSeconds();
    var aSecondsStr = "" + aSeconds;
    if( aSecondsStr.length < 2) {
        aSecondsStr = "0" + aSecondsStr;
    }

    var aMilliseconds = theDate.getMilliseconds();
    var aMillisecondsStr = "" + aMilliseconds;
    if( aMillisecondsStr.length < 3) {
        if( aMillisecondsStr.length < 2) {
            aMillisecondsStr = "00" + aMillisecondsStr;
        }
        else {
            aMillisecondsStr = "0" + aMillisecondsStr;
        }
    }


    var aTimestampStr = aFullYearStr +aMonthStr + aDateStr +
        + aHoursStr + aMinutesStr + aSecondsStr;

    return aTimestampStr;
}



main();