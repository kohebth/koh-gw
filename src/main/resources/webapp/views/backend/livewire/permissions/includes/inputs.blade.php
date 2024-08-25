<div class="form-group row">
    {{ html()->label('Name <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('name') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="permission.name" class="form-control">
        @error('permission.name')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>

<div class="form-group row">
    {{ html()->label('Guard Name <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('guard_name') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="permission.guard_name" class="form-control" value="web" readonly>
        @error('permission.guard_name')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
