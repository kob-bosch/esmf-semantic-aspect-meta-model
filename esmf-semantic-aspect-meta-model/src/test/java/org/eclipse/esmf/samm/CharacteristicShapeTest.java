/*
 * Copyright (c) 2023 Robert Bosch Manufacturing Solutions GmbH
 *
 * See the AUTHORS file(s) distributed with this work for additional
 * information regarding authorship.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.eclipse.esmf.samm;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import org.eclipse.esmf.samm.validation.SemanticError;

public class CharacteristicShapeTest extends AbstractShapeTest {
   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceValidationExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "characteristic-shape", "TestCharacteristicInstance", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicSubClassValidationExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "characteristic-shape", "TestCharacteristicSubClass", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceMissingRequiredPropertiesExpectFailure2(
         final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String instanceName = "TestCharacteristicInstanceMissingRequiredProperties";
      final String instanceId = TEST_NAMESPACE_PREFIX + instanceName;
      final SemanticError resultForDataType = new SemanticError(
            validator.getMessageText( "samm:CharacteristicShape", "samm:dataType", "ERR_NO_DATATYPE", metaModelVersion ),
            instanceId, sammUrns.datatypeUrn, VIOLATION_URN, "" );
      expectSemanticValidationErrors( "characteristic-shape", instanceName, metaModelVersion, resultForDataType );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceNotRequiredToSetDataTypeExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "characteristic-shape", "TestCharacteristicInstanceNotRequiredToSetDataType",
            metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceEmptyPropertiesExpectFailure2( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String instanceName = "TestCharacteristicInstanceWithEmptyProperties";
      final String instanceUri = TEST_NAMESPACE_PREFIX + instanceName;
      final SemanticError resultForPreferredName = new SemanticError( MESSAGE_EMPTY_PROPERTY,
            instanceUri, sammUrns.preferredNameUrn, VIOLATION_URN, "@en" );
      final SemanticError resultForDescription = new SemanticError( MESSAGE_EMPTY_PROPERTY,
            instanceUri, sammUrns.descriptionUrn, VIOLATION_URN, "@en" );
      expectSemanticValidationErrors( "characteristic-shape", instanceName,
            metaModelVersion, resultForPreferredName, resultForDescription );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicWithGDay( final KnownVersion metaModelVersion ) {
      checkValidity( "characteristic-shape", "TestCharacteristicInstanceWithGDay",
            metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceInvalidLanguageStringsExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String instanceName = "TestCharacteristicInstanceWithInvalidLangStrings";
      final String instanceId = TEST_NAMESPACE_PREFIX + instanceName;
      final SemanticError resultForPreferredName = new SemanticError( MESSAGE_INVALID_LANG_STRING, instanceId,
            sammUrns.preferredNameUrn, VIOLATION_URN, "Test Characteristic Instance" );
      final SemanticError resultForDescription = new SemanticError( MESSAGE_INVALID_LANG_STRING, instanceId,
            sammUrns.descriptionUrn, VIOLATION_URN, "Test Characteristic Instance" );
      expectSemanticValidationErrors( "characteristic-shape", instanceName,
            metaModelVersion, resultForPreferredName, resultForDescription );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceLanguageStringNotUniqueExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String instanceName = "TestCharacteristicInstanceNonUniqueLangStrings";
      final String instanceId = TEST_NAMESPACE_PREFIX + instanceName;
      final SemanticError resultForPreferredName = new SemanticError( MESSAGE_LANG_NOT_UNIQUE,
            instanceId, sammUrns.preferredNameUrn, VIOLATION_URN, "" );
      final SemanticError resultForDescription = new SemanticError( MESSAGE_LANG_NOT_UNIQUE,
            instanceId, sammUrns.descriptionUrn, VIOLATION_URN, "" );
      expectSemanticValidationErrors( "characteristic-shape", instanceName,
            metaModelVersion, resultForPreferredName, resultForDescription );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicSubClassLanguageStringNotUniqueExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String className = "TestCharacteristicSubClassNonUniqueLangStrings";
      final String classId = TEST_NAMESPACE_PREFIX + className;
      final SemanticError resultForPreferredName = new SemanticError( MESSAGE_LANG_NOT_UNIQUE,
            classId, sammUrns.preferredNameUrn, VIOLATION_URN, "" );
      final SemanticError resultForDescription = new SemanticError( MESSAGE_LANG_NOT_UNIQUE,
            classId, sammUrns.descriptionUrn, VIOLATION_URN, "" );
      expectSemanticValidationErrors( "characteristic-shape", className,
            metaModelVersion, resultForPreferredName, resultForDescription );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceMultipleDataTypesExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String instanceName = "TestCharacteristicInstanceWithMultipleDataTypes";
      final String instanceId = TEST_NAMESPACE_PREFIX + instanceName;
      final SemanticError resultForDataType = new SemanticError(
            MESSAGE_DUPLICATE_PROPERTY, instanceId, sammUrns.datatypeUrn, VIOLATION_URN, "" );
      expectSemanticValidationErrors( "characteristic-shape", instanceName, metaModelVersion, resultForDataType );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicSubClassMultipleDataTypesExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String className = "TestCharacteristicSubClassWithMultipleDataTypes";
      final String classId = TEST_NAMESPACE_PREFIX + className;
      final SemanticError resultForDataType = new SemanticError( MESSAGE_DUPLICATE_PROPERTY,
            classId, sammUrns.datatypeUrn, VIOLATION_URN, "" );
      expectSemanticValidationErrors( "characteristic-shape", className, metaModelVersion, resultForDataType );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceDefinesDisallowedDataType( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String instanceName = "TestCharacteristicInstanceWithDisallowedDataType";
      final String instanceId = TEST_NAMESPACE_PREFIX + instanceName;
      final SemanticError resultForDataType = new SemanticError( MESSAGE_INVALID_DATA_TYPE,
            instanceId, sammUrns.datatypeUrn, VIOLATION_URN, "http://www.w3.org/2001/XMLSchema#maxExclusive" );
      expectSemanticValidationErrors( "characteristic-shape", instanceName, metaModelVersion, resultForDataType );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testCharacteristicInstanceWithCharacteristicAsDataTypeExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final String instanceName = "TestCharacteristicInstanceWithCharacteristicAsDataType";
      final String instanceId = TEST_NAMESPACE_PREFIX + instanceName;
      final String value = String.format( "urn:samm:org.eclipse.esmf.samm:characteristic:%s#Text", metaModelVersion.toVersionString() );
      final SemanticError resultForDataType = new SemanticError( MESSAGE_INVALID_DATA_TYPE,
            instanceId, sammUrns.datatypeUrn, VIOLATION_URN, value );
      expectSemanticValidationErrors( "characteristic-shape", instanceName, metaModelVersion, resultForDataType );
   }
}
