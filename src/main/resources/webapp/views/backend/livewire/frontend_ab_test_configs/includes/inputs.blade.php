<div class="form-group row">
    {{ html()->label('Name <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('name') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="frontendAbTestConfig.name" class="form-control">
        @error('frontendAbTestConfig.name')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
