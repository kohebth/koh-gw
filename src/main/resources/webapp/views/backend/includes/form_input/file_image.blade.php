@if (!empty($url)) <img width="300" src="{{ $url }}" alt=""/><hr/> @endif
<input type="file" name="{{$name}}-upload" accept="image/png,image/jpg,image/jpeg,image/gif" class="form-control">
<div class="text-muted font-xs">(Maximum upload file size: 2MB. Supported file types: png, jpg, jpeg, gif)</div>
<span id='{{$name}}-error' class="text-danger d-none" wire:ignore></span>
@error($name)
    @php $errorMessage = $message; @endphp
    <x-error-message>{{ $message }}</x-error-message>
@enderror
@if($file)
    @php
        try {
            $urlTemp = \App\Helpers\Helper::getTemporaryUploadedUrl($file);
        } catch (\App\Exceptions\FailTemporaryUploadException $exception) {
            $messageTemp = $exception->getMessage();
        }
    @endphp
@endif
@if(empty($errorMessage))
    @if(isset($messageTemp))
        <x-error-message>{{ $messageTemp }}</x-error-message>
    @endif
    @if (isset($urlTemp))
        <div class="mt-2">Preview:</div>
        <img height="150px" src="{{ $urlTemp }}" alt="">
    @endif
@endif
@push('after-scripts')
    <script>
        $('input[name="{{$name}}-upload"]').on('change', function(e) {
            const file = this.files[0]
            if(!file) {
                return false;
            }

            const size = file.size || 0;
            const fileExtension = ['png', 'jpg', 'jpeg', 'gif'];
            let error = $('#{{$name}}-error');
            if (!file.type.includes('image/')) {
                error.html('The file must be an image.').removeClass('d-none');
                @this.set('{{$name}}', null);
                return false;
            }
            if ($.inArray(file.name.split('.').pop().toLowerCase(), fileExtension) === -1) {
                error.html('The file must be a file of type: '+fileExtension.join(', ')+'.').removeClass('d-none');
                @this.set('{{$name}}', null);
                return false;
            }
            if(size > 1024 * 1024 * 2) {
                error.html('The file may not be greater than 2048 kilobytes.').removeClass('d-none');
                @this.set('{{$name}}', null);
                return false;
            }
            error.addClass('d-none');
            @this.set('{{$name}}', null);
            @this.upload('{{$name}}', file);
        });
    </script>
@endpush
