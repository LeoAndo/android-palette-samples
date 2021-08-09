# Overview
imageViewのBitmapから色を抽出してくれるライブラリ(palette)のAPI調査用のアプリです。<br>
paletteライブラリは1.0リリース後にUpdateがなく、ライブラリ内部でAndroid 11で非推奨APIになった、`AsyncTask`を使っていたりしているので、利用しない方が良いかも<br>

<img width="1200" alt="スクリーンショット 2021-08-09 23 32 27" src="https://user-images.githubusercontent.com/16476224/128723569-41aa22ea-589e-4c79-8ae7-3102881905c3.png">

# development environment
<img width="686" alt="127751156-0638bd97-e532-43e9-be12-e758a7118141" src="https://user-images.githubusercontent.com/16476224/128724724-5c24d861-5030-4b8d-93b0-020efa716907.png">

# capture
<img src="https://user-images.githubusercontent.com/16476224/128722209-6ccbbae1-c16d-42a0-848b-35769e48abc0.gif" width=320 />

# palette Libarry
```groovy
implementation("androidx.palette:palette:1.0.0")
implementation("androidx.palette:palette-ktx:1.0.0")
```

# For Java code
https://github.com/LeoAndo/android-palette-samples/tree/main/PaletteJavaSample

# For Kotlin code
https://github.com/LeoAndo/android-palette-samples/tree/main/PaletteJavaSample

# testData(image url)
pokemon png<br>
https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png<br>
https://assets.pokemon.com/assets/cms2/img/pokedex/full/500.png<br>

pokemon gif<br>
https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/1.gif<br>
https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/500.gif<br>


# refs
https://developer.android.com/reference/androidx/palette/graphics/package-summary
