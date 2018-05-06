"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const httpm = require("typed-rest-client/HttpClient");
const fs = require("async-file");
const URL_SPRINGNUT_BASE = "http://localhost:8080/springnut/";
const URL_STEALMODEL_PATH = URL_SPRINGNUT_BASE + "stealmodel_jpa/model/";
const URL_ENTITYNAMES = URL_STEALMODEL_PATH + "entityNames";
const URL_ENTITYSPEC = URL_STEALMODEL_PATH + "entitySpec/";
const URL_FINALSLASH = "/";
const METADATA_BASEDIR = "./src/sourcemeta";
const METADATA_DIRNAME = "jpameta";
const METADATA_DIR = METADATA_BASEDIR + "/" + METADATA_DIRNAME;
function main() {
    return __awaiter(this, void 0, void 0, function* () {
        yield ensureTargetDirExists();
        const someJPAmetadata = yield fetchAllJPAmetadata();
        console.log("Successfully retrieved AllJPAmetadata");
        /*
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
        */
    });
}
function ensureTargetDirExists() {
    return __awaiter(this, void 0, void 0, function* () {
        let aDirBefore = yield fs.readdir(METADATA_BASEDIR);
        console.log("List before\n" + aDirBefore);
        if (aDirBefore.indexOf(METADATA_DIRNAME) >= 0) {
            return;
        }
        yield fs.mkdir(METADATA_DIR);
        let aDirAfter = yield fs.readdir(METADATA_BASEDIR);
        console.log(aDirAfter);
        if (aDirAfter.indexOf(METADATA_DIRNAME) < 0) {
            throw "Can not create " + METADATA_DIRNAME;
        }
    });
}
function writeToTargetDir(theFilename, theFilecontents) {
    return __awaiter(this, void 0, void 0, function* () {
        let aFullPath = METADATA_DIR + "/" + theFilename + ".json";
        console.log("Writing to file " + aFullPath);
        let aFile = yield fs.open(aFullPath, 'w');
        try {
            yield fs.writeFile(aFile, theFilecontents);
        }
        finally {
            if (aFile) {
                yield fs.close(aFile);
            }
            aFile = 0;
        }
    });
}
let httpc = undefined;
function lazyHttpClient() {
    if (httpc) {
        return httpc;
    }
    httpc = new httpm.HttpClient('vsts-node-api');
    return httpc;
}
function fetchAllJPAmetadata() {
    return __awaiter(this, void 0, void 0, function* () {
        /*     export function get(options: RequestOptions | string | URL, callback?: (res: IncomingMessage) => void): ClientRequest;
        */
        console.info("Retrieving entity names " + URL_ENTITYNAMES);
        const anEntityNamesResponse = yield lazyHttpClient().get(URL_ENTITYNAMES);
        const anEntityNamesBody = yield anEntityNamesResponse.readBody();
        console.info("Response received for entity names request " + URL_ENTITYNAMES);
        if (typeof anEntityNamesBody == "undefined") {
            return;
        }
        let someEntityNames = undefined;
        try {
            someEntityNames = JSON.parse(anEntityNamesBody);
        }
        catch (anException) {
        }
        if (!someEntityNames || !someEntityNames.length) {
            return;
        }
        console.info("Received " + someEntityNames.length + " entity names from request " + URL_ENTITYNAMES);
        const someResults = [];
        for (let anEntityName of someEntityNames) {
            const aURI = URL_ENTITYSPEC + anEntityName + URL_FINALSLASH;
            console.info("Retrieving meta for entity " + anEntityName + " " + aURI);
            const anEntityResponse = yield lazyHttpClient().get(aURI);
            const anEntityBody = yield anEntityResponse.readBody();
            console.info("Response received for entity " + anEntityName + " " + aURI);
            if (!(typeof anEntityBody == "undefined")) {
                let anEntityMeta = undefined;
                try {
                    anEntityMeta = JSON.parse(anEntityBody);
                }
                catch (anException) {
                }
                if (!anEntityMeta) {
                    continue;
                }
                console.info("Meta received for entity " + anEntityName + " " + aURI);
                yield writeToTargetDir(anEntityName, JSON.stringify(anEntityMeta, null, 4));
                someResults.push({ "URI": aURI, "meta": anEntityMeta });
            }
        }
        //  console.log( JSON.stringify( someResults, null, 4));
        return someResults;
    });
}
function fDateToDateStr(theDate) {
    var aUTCDate = "" + theDate.getDate();
    var aUTCDateStr = "" + aUTCDate;
    if (aUTCDateStr.length < 2) {
        aUTCDateStr = "0" + aUTCDateStr;
    }
    var aUTCMonth = "" + (theDate.getMonth() + 1);
    var aUTCMonthStr = "" + aUTCMonth;
    if (aUTCMonthStr.length < 2) {
        aUTCMonthStr = "0" + aUTCMonthStr;
    }
    var aUTCFullYear = theDate.getFullYear();
    var aUTCFullYearStr = "" + aUTCFullYear;
    var aDateStr = aUTCDateStr + "/" + aUTCMonthStr + "/" + aUTCFullYearStr;
    return aDateStr;
}
function fDateToDateWOsepsStr(theDate) {
    var aUTCDate = "" + theDate.getDate();
    var aUTCDateStr = "" + aUTCDate;
    if (aUTCDateStr.length < 2) {
        aUTCDateStr = "0" + aUTCDateStr;
    }
    var aUTCMonth = "" + (theDate.getMonth() + 1);
    var aUTCMonthStr = "" + aUTCMonth;
    if (aUTCMonthStr.length < 2) {
        aUTCMonthStr = "0" + aUTCMonthStr;
    }
    var aUTCFullYear = theDate.getFullYear();
    var aUTCFullYearStr = "" + aUTCFullYear;
    var aDateStr = aUTCFullYearStr + aUTCMonthStr + aUTCDateStr;
    return aDateStr;
}
function fDateToTimestampStr(theDate) {
    if (theDate == null) {
        return null;
    }
    var aDate = "" + theDate.getDate();
    var aDateStr = "" + aDate;
    if (aDateStr.length < 2) {
        aDateStr = "0" + aDateStr;
    }
    var aMonth = "" + (theDate.getMonth() + 1);
    var aMonthStr = "" + aMonth;
    if (aMonthStr.length < 2) {
        aMonthStr = "0" + aMonthStr;
    }
    var aFullYear = theDate.getFullYear();
    var aFullYearStr = "" + aFullYear;
    var aHours = theDate.getHours();
    var aHoursStr = "" + aHours;
    if (aHoursStr.length < 2) {
        aHoursStr = "0" + aHoursStr;
    }
    var aMinutes = theDate.getMinutes();
    var aMinutesStr = "" + aMinutes;
    if (aMinutesStr.length < 2) {
        aMinutesStr = "0" + aMinutesStr;
    }
    var aSeconds = theDate.getSeconds();
    var aSecondsStr = "" + aSeconds;
    if (aSecondsStr.length < 2) {
        aSecondsStr = "0" + aSecondsStr;
    }
    var aMilliseconds = theDate.getMilliseconds();
    var aMillisecondsStr = "" + aMilliseconds;
    if (aMillisecondsStr.length < 3) {
        if (aMillisecondsStr.length < 2) {
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
function fDateToTimestampWOsepsStr(theDate) {
    var aDate = "" + theDate.getDate();
    var aDateStr = "" + aDate;
    if (aDateStr.length < 2) {
        aDateStr = "0" + aDateStr;
    }
    var aMonth = "" + (theDate.getMonth() + 1);
    var aMonthStr = "" + aMonth;
    if (aMonthStr.length < 2) {
        aMonthStr = "0" + aMonthStr;
    }
    var aFullYear = theDate.getFullYear();
    var aFullYearStr = "" + aFullYear;
    var aHours = theDate.getHours();
    var aHoursStr = "" + aHours;
    if (aHoursStr.length < 2) {
        aHoursStr = "0" + aHoursStr;
    }
    var aMinutes = theDate.getMinutes();
    var aMinutesStr = "" + aMinutes;
    if (aMinutesStr.length < 2) {
        aMinutesStr = "0" + aMinutesStr;
    }
    var aSeconds = theDate.getSeconds();
    var aSecondsStr = "" + aSeconds;
    if (aSecondsStr.length < 2) {
        aSecondsStr = "0" + aSecondsStr;
    }
    var aMilliseconds = theDate.getMilliseconds();
    var aMillisecondsStr = "" + aMilliseconds;
    if (aMillisecondsStr.length < 3) {
        if (aMillisecondsStr.length < 2) {
            aMillisecondsStr = "00" + aMillisecondsStr;
        }
        else {
            aMillisecondsStr = "0" + aMillisecondsStr;
        }
    }
    var aTimestampStr = aFullYearStr + aMonthStr + aDateStr +
        +aHoursStr + aMinutesStr + aSecondsStr;
    return aTimestampStr;
}
main();
//# sourceMappingURL=jpatometa.js.map