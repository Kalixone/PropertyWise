package com.propertywise.exceptions

class PropertyNotFoundException(identifier: String) : Exception("Property with identifier '$identifier' not found")
