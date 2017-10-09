/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
'use strict';

var FLOWABLE = FLOWABLE || {};
FLOWABLE.PROPERTY_CONFIG =
{
    "string": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/default-value-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/string-property-write-mode-template.html"
    },
    "boolean": {
        "templateUrl": "/plugins/flow/editor-app/configuration/properties/boolean-property-template.html"
    },
    "text" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/default-value-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/text-property-write-template.html"
    },
    "flowable-multiinstance" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/default-value-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/multiinstance-property-write-template.html"
    },
    "flowable-ordering" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/default-value-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/ordering-property-write-template.html"
    },
    "oryx-formproperties-complex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/form-properties-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/form-properties-write-template.html"
    },
    "oryx-executionlisteners-multiplecomplex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/execution-listeners-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/execution-listeners-write-template.html"
    },
    "oryx-tasklisteners-multiplecomplex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/task-listeners-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/task-listeners-write-template.html"
    },
    "oryx-eventlisteners-multiplecomplex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/event-listeners-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/event-listeners-write-template.html"
    },
    "oryx-usertaskassignment-complex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/assignment-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/assignment-write-template.html"
    },
    "oryx-servicetaskfields-complex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/fields-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/fields-write-template.html"
    },
    "oryx-callactivityinparameters-complex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/in-parameters-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/in-parameters-write-template.html"
    },
    "oryx-callactivityoutparameters-complex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/out-parameters-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/out-parameters-write-template.html"
    },
    "oryx-subprocessreference-subprocess-link": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/subprocess-reference-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/subprocess-reference-write-template.html"
    },
    "oryx-formreference-complex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/form-reference-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/form-reference-write-template.html"
    },
    "oryx-sequencefloworder-complex" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/sequenceflow-order-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/sequenceflow-order-write-template.html"
    },
    "oryx-conditionsequenceflow-complex" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/condition-expression-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/condition-expression-write-template.html"
    },
    "oryx-signaldefinitions-multiplecomplex" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/signal-definitions-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/signal-definitions-write-template.html"
    },
    "oryx-signalref-string" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/default-value-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/signal-property-write-template.html"
    },
    "oryx-messagedefinitions-multiplecomplex" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/message-definitions-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/message-definitions-write-template.html"
    },
    "oryx-messageref-string" : {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/default-value-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/message-property-write-template.html"
    },
    "oryx-duedatedefinition-complex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/duedate-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/duedate-write-template.html"
    },
    "oryx-decisiontaskdecisiontablereference-complex": {
        "readModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/decisiontable-reference-display-template.html",
        "writeModeTemplateUrl": "/plugins/flow/editor-app/configuration/properties/decisiontable-reference-write-template.html"
    }
};
